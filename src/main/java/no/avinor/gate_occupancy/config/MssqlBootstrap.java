package no.avinor.gate_occupancy.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.model.entities.Airport;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.model.entities.Terminal;
import no.avinor.gate_occupancy.repository.AirportRepository;
import no.avinor.gate_occupancy.repository.LocationRelationshipRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import no.avinor.gate_occupancy.repository.TerminalRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MssqlBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private final LocationRepository locationRepository;
    private final AirportRepository airportRepository;
    private final TerminalRepository terminalRepository;
    private final LocationRelationshipRepository relationshipRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        loadAirportData();
        loadTerminalData();
        loadLocationData();
        loadNearbyLocationData();
    }

    private void loadAirportData() {
        try (InputStream inputStream = new ClassPathResource("dbSetup/airport.json").getInputStream()) {
            log.info("Loading airports...");
            List<Map<String, Object>> airports = objectMapper.readValue(inputStream, new TypeReference<>() {});

            for (Map<String, Object> airportData : airports) {
                String airportIata = airportData.get("iata").toString();
                if (airportRepository.existsByIata(airportIata)) {
                    continue;
                }

                Airport airport = new Airport()
                        .setIata(airportData.get("iata").toString())
                        .setName(airportData.get("name").toString())
                        .setCity(airportData.get("city").toString())
                        .setSchengen((Boolean) airportData.get("schengen"));

                airportRepository.save(airport);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading airport data", e);
        }
    }

    private void loadTerminalData() {
        try (InputStream inputStream = new ClassPathResource("dbSetup/terminal.json").getInputStream()) {
            log.info("Loading terminals...");
            List<Map<String, Object>> terminals = objectMapper.readValue(inputStream, new TypeReference<>() {});

            for (Map<String, Object> terminalData : terminals) {
                String terminalName = terminalData.get("name").toString();
                String airportIata = terminalData.get("airportIata").toString();

                if (terminalRepository.existsByNameAndAirport_Iata(terminalName, airportIata)) {
                    continue;
                }

                Airport parentAirport = airportRepository.findByIata(airportIata)
                        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.AIRPORT_NOT_FOUND));

                Terminal terminal = new Terminal()
                        .setName(terminalName)
                        .setAirport(parentAirport)
                        .setNumberOfGates((Integer) terminalData.get("numberOfGates"));

                terminalRepository.save(terminal);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading terminal data", e);
        }
    }

    private void loadLocationData() {
        try (InputStream inputStream = new ClassPathResource("dbSetup/location.json").getInputStream()) {
            log.info("Loading locations...");
            List<Map<String, Object>> locations = objectMapper.readValue(inputStream, new TypeReference<>() {});

            for (Map<String, Object> locationData : locations) {
                String locationName = locationData.get("name").toString();
                String airportIata = locationData.get("airportIata").toString();
                if (locationRepository.existsByNameAndAndTerminal_Airport_Iata(locationName, airportIata)) {
                    continue;
                }

                String terminalName = locationData.get("terminalName").toString();

                Terminal parentTerminal = terminalRepository.findByNameAndAirport_Iata(terminalName, airportIata)
                        .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.TERMINAL_NOT_FOUND));

                String typeString = locationData.get("type").toString();
                LocationType locationType = LocationType.valueOf(typeString.toUpperCase());

                Location location = new Location()
                        .setTerminal(parentTerminal)
                        .setType(locationType)
                        .setName(locationName)
                        .setCapacity((Integer) locationData.get("capacity"));

                locationRepository.save(location);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading location data", e);
        }
    }

    @Transactional
    protected void loadNearbyLocationData() {
        if (relationshipRepository.count() > 0) {
            log.info("Nearby locations already exist. Skipping.");
            return;
        }
        log.info("Loading nearby locations...");
        try (InputStream inputStream = new ClassPathResource("dbSetup/nearby_locations.json").getInputStream()) {

            Map<String, List<Map<String, Object>>> nearbyMap = objectMapper.readValue(inputStream, new TypeReference<>() {});

            for (Map.Entry<String, List<Map<String, Object>>> entry : nearbyMap.entrySet()) {
                String sourceLocationKey = entry.getKey();
                List<Map<String, Object>> neighborObjects = entry.getValue();

                Location sourceLocation = findLocationByKey(sourceLocationKey);
                if (sourceLocation == null) {
                    log.warn("Source location not found for key: {}", sourceLocationKey);
                    continue;
                }

                for (Map<String, Object> neighborInfo : neighborObjects) {
                    String neighborKey = neighborInfo.get("neighborKey").toString();
                    Integer walkingTime = (Integer) neighborInfo.get("walkingTimeInMinutes");

                    Location neighborLocation = findLocationByKey(neighborKey);

                    if (neighborLocation != null) {
                        LocationRelationship relationship = new LocationRelationship()
                                .setSourceLocation(sourceLocation)
                                .setTargetLocation(neighborLocation)
                                .setWalkingTimeInMinutes(walkingTime);

                        relationshipRepository.save(relationship);
                    } else {
                        log.warn("Neighbor location not found for key: {}", neighborKey);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading nearby location data", e);
        }
    }


    private Location findLocationByKey(String key) {
        String[] parts = key.split("_");
        if (parts.length != 2) return null;

        String airportIata = parts[0];
        String locationName = parts[1];

        return locationRepository.findByNameAndTerminal_Airport_Iata(locationName, airportIata);
    }
}