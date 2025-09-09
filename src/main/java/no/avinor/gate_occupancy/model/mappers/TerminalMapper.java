package no.avinor.gate_occupancy.model.mappers;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalResponse;
import no.avinor.gate_occupancy.model.entities.Terminal;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TerminalMapper {

    private final LocationMapper locationMapper;

    public TerminalResponse toResponse(Terminal terminal) {
        if (terminal == null) {
            return null;
        }

        List<LocationLiteResponse> locations = locationMapper.toLiteResponseList(terminal.getLocations().stream().toList());

        return new TerminalResponse(
                terminal.getId(),
                terminal.getName(),
                locations
        );
    }

    public List<TerminalResponse> toResponseList(List<Terminal> terminals) {
        if (terminals == null || terminals.isEmpty()) {
            return Collections.emptyList();
        }

        return terminals.stream()
                .map(this::toResponse)
                .toList();
    }

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
