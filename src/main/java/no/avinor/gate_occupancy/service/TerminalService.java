package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.model.entities.Terminal;
import no.avinor.gate_occupancy.repository.TerminalRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TerminalService {

    private final TerminalRepository terminalRepository;

    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }

}
