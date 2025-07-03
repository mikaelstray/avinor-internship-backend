package no.avinor.gate_occupancy.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.repository.AirportRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
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
    private final AirportRepository
    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        loadLocationData();
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

                    // hvis eksisterer --> continue
                    if (locationRepository.existsById((Long) locationData.get("location_id"))) {
                        continue;
                    }

                    // nytt objekt i database
                    Location location = new Location()
                            .setCapacity(10)
                            .setName("gate1")
                            .setType(LocationType.GATE)
                            .setZone(null);

                    // .save()
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading location data", e);
        }
    }
}