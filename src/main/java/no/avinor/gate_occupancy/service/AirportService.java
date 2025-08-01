package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.model.entities.Airport;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.model.entities.Terminal;
import no.avinor.gate_occupancy.repository.AirportRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import no.avinor.gate_occupancy.repository.TerminalRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AirportService {

    private final AirportRepository airportRepository;
    private final TerminalRepository terminalRepository;
    private final LocationRepository locationRepository;
    private static final Pattern numberPattern = Pattern.compile("(\\D*)(\\d+)(.*)");

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public List<Terminal> getTerminalsByAirportIata(String iata) {
        List<Terminal> terminals = terminalRepository.findTerminalsWithGatesByAirportIata(iata);
        terminals.forEach(terminal -> {
            terminal.getLocations().sort(new GateNameComparator());
        });

        return terminals;
    }

    public List<Location> getGatesByAirportIata(String iata) {
        return locationRepository.findByTypeAndTerminal_Airport_Iata(LocationType.GATE, iata);
    }

    private static class GateNameComparator implements Comparator<Location> {
        @Override
        public int compare(Location loc1, Location loc2) {
            Matcher m1 = numberPattern.matcher(loc1.getName());
            Matcher m2 = numberPattern.matcher(loc2.getName());

            if (m1.matches() && m2.matches()) {
                int prefixCompare = m1.group(1).compareTo(m2.group(1));
                if (prefixCompare != 0) {
                    return prefixCompare;
                }

                int num1 = Integer.parseInt(m1.group(2));
                int num2 = Integer.parseInt(m2.group(2));
                return Integer.compare(num1, num2);
            }

            return loc1.getName().compareTo(loc2.getName());
        }
    }
}
