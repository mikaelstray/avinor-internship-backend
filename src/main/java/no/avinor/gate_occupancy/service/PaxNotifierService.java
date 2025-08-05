package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.LocationOccupancyStatus;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.mappers.LiveOccupancyMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaxNotifierService {
    private final SimpMessagingTemplate messagingTemplate;
    private final LiveOccupancyMapper mapper;
    private static final Logger logger = LogManager.getLogger(PaxNotifierService.class);

    public void notifyPaxUpdate(LocationLiveOccupancy liveOccupancy) {
        String dest = "/topic/pax-updates";
        LocationOccupancyStatus response = mapper.toDto(liveOccupancy);

        if (response != null) {
            messagingTemplate.convertAndSend(dest, response);
        }
    }
}
