package no.avinor.gate_occupancy.controller;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.service.AreaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
@Validated

public class AreaController {

    private static final Logger logger = LogManager.getLogger(AreaController.class);

}
