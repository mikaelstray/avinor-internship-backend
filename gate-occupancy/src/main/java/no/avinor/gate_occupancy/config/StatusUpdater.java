package no.avinor.gate_occupancy.config;

import no.avinor.gate_occupancy.model.entities.CapacityStatus;
import no.avinor.gate_occupancy.model.entities.Crowdiness;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class StatusUpdater {

    private final CapacityStatusRepository capacityStatusRepository;

    public StatusUpdater(CapacityStatusRepository capacityStatusRepository) {
        this.capacityStatusRepository = capacityStatusRepository;
    }

    /**
     * Simulerer endring av crowdiness-status hvert 6. sekund
     */
    @Scheduled(fixedRate = 6000) // 6000 ms = 6 sek
    public void updateCrowdiness() {
        Long areaId = 101L;

        Optional<CapacityStatus> optStatus = capacityStatusRepository.findLatestByAreaId(areaId);

        if (optStatus.isPresent()) {
            CapacityStatus status = optStatus.get();
            Crowdiness current = status.getCrowdiness();

            //LOW → MODERATE → HIGH → LOW ...
            Crowdiness next = switch (current) {
                case LOW -> Crowdiness.MODERATE;
                case MODERATE -> Crowdiness.HIGH;
                case HIGH -> Crowdiness.LOW;
                default -> Crowdiness.LOW;
            };

            status.setCrowdiness(next);
            status.setTimestamp(LocalDateTime.now());

            System.out.println("Oppdatert crowdiness til: " + next);
        }
    }
}
