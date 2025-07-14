package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.entities.Terminal;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class TerminalMapper {

    public TerminalLiteResponse toLiteResponse(Terminal terminal) {
        if (terminal == null) {
            return null;
        }

        return new TerminalLiteResponse(
                terminal.getId(),
                terminal.getName()
        );
    }

    public List<TerminalLiteResponse> toLiteResponseList(List<Terminal> terminals) {
        if (terminals == null || terminals.isEmpty()) {
            return Collections.emptyList();
        }

        return terminals.stream()
                .map(this::toLiteResponse)
                .toList();
    }

}
