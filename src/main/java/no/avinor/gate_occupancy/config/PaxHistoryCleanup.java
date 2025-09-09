package no.avinor.gate_occupancy.config;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.repository.LocationOccupancyHistoryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PaxHistoryCleanup {

    LocationOccupancyHistoryRepository history;

    @Transactional
    @Scheduled(cron = "0 0 2 * * *") //  2 AM daily
    public void cleanupOldHistory() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(3);
        history.deleteByCreatedAtBefore(cutoffDate);
    }
}