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
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.model.entities.Terminal;
import no.avinor.gate_occupancy.model.entities.Zone;
import no.avinor.gate_occupancy.repository.AirportRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import no.avinor.gate_occupancy.repository.TerminalRepository;
import no.avinor.gate_occupancy.repository.ZoneRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MssqlBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private final LocationRepository locationRepository;
    private final AirportRepository airportRepository;
    private final ZoneRepository zoneRepository;
    private final TerminalRepository terminalRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        loadAirportData();
        loadTerminalData();
        loadZoneData();
        loadLocationData();
    }

    private void loadAirportData() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbSetup/airport.json")) {
            if (inputStream == null) {
                throw new RuntimeException("airport.json not found in classpath");
            }
            log.info("Loading airports...");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                List<Map<String, Object>> airports = objectMapper.readValue(br, new TypeReference<>() {
                });
                System.out.println(airports);
                for (Map<String, Object> airportData : airports) {

                    if (airportRepository.existsByName(airportData.get("name").toString())) {
                        continue;
                    }

                    Airport airport = new Airport()
                            .setName(airportData.get("name").toString())
                            .setCity(airportData.get("city").toString())
                            .setSchengen((Boolean) airportData.get("schengen"));

                    airportRepository.save(airport);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading airport data", e);
        }
    }

    private void loadTerminalData() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbSetup/terminal.json")) {
            if (inputStream == null) {
                throw new RuntimeException("terminal.json not found in classpath");
            }
            log.info("Loading terminal...");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                List<Map<String, Object>> terminals = objectMapper.readValue(br, new TypeReference<>() {
                });
                for (Map<String, Object> terminalData : terminals) {

                    if (terminalRepository.existsByName(terminalData.get("name").toString())) {
                        continue;
                    }

                    String airportName = terminalData.get("airportName").toString();
                    Airport parentAirport = airportRepository.findByName(airportName)
                            .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.AIRPORT_NOT_FOUND));

                    Terminal terminal = new Terminal()
                            .setName(terminalData.get("name").toString())
                            .setAirport(parentAirport)
                            .setNumberOfGates((Integer) terminalData.get("numberOfGates"));

                    terminalRepository.save(terminal);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading terminal data", e);
        }
    }

    private void loadZoneData() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbSetup/zone.json")) {
            if (inputStream == null) {
                throw new RuntimeException("zone.json not found in classpath");
            }
            log.info("Loading zone...");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                List<Map<String, Object>> zones = objectMapper.readValue(br, new TypeReference<>() {
                });
                for (Map<String, Object> zoneData : zones) {

                    if (zoneRepository.existsByName(zoneData.get("name").toString())) {
                        continue;
                    }

                    String terminalName = zoneData.get("terminalName").toString();
                    Terminal parentTerminal = terminalRepository.findByName(terminalName)
                            .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.TERMINAL_NOT_FOUND));

                    Zone zone = new Zone()
                            .setName(zoneData.get("name").toString())
                            .setCapacity((Integer) zoneData.get("capacity"))
                            .setTerminal(parentTerminal);

                    zoneRepository.save(zone);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading zone data", e);
        }
    }


    private void loadLocationData() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbSetup/location.json")) {
            if (inputStream == null) {
                throw new RuntimeException("location.json not found in classpath");
            }
            log.info("Loading locations...");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                List<Map<String, Object>> locations = objectMapper.readValue(br, new TypeReference<>() {
                });
                for (Map<String, Object> locationData : locations) {

                    if (locationRepository.existsByName(locationData.get("name").toString())) {
                        continue;
                    }

                    String zoneName = locationData.get("zoneName").toString();
                    Zone parentZone = zoneRepository.findByName(zoneName)
                            .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.ZONE_NOT_FOUND));

                    String typeString = locationData.get("type").toString();
                    LocationType locationType = LocationType.valueOf(typeString.toUpperCase());

                    Location location = new Location()
                            .setZone(parentZone)
                            .setType(locationType)
                            .setName(locationData.get("name").toString())
                            .setCapacity((Integer) locationData.get("capacity"));

                    locationRepository.save(location);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading location data", e);
        }
    }
}