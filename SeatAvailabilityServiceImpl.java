package com.MainBus.implementation;

import com.MainBus.DTO.SeatAvailabilityWithStopName;
import com.MainBus.Model.BusStop;
import com.MainBus.Model.SeatAvailability;
import com.MainBus.Repository.BusStopRepository;
import com.MainBus.Repository.SeatAvailabilityRepository;
import com.MainBus.Service.SeatAvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {

    private final SeatAvailabilityRepository repository;
    private final BusStopRepository busStopRepository;  // Add BusStopRepository for querying stop names

    public SeatAvailabilityServiceImpl(SeatAvailabilityRepository repository, BusStopRepository busStopRepository) {
        this.repository = repository;
        this.busStopRepository = busStopRepository;  // Inject BusStopRepository
    }

    @Override
    public List<SeatAvailabilityWithStopName> getAvailabilityByBusNumber(String busNumber) {
        List<SeatAvailability> seatAvailabilities = repository.findByBusNumber(busNumber);

        // Convert SeatAvailability to SeatAvailabilityWithStopName DTO
        return seatAvailabilities.stream().map(seat -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String lastUpdated = seat.getLastUpdated() != null ? seat.getLastUpdated().format(formatter) : "N/A";

            // Fetch the actual stop name from BusStop entity
            String stopName = fetchStopName(seat.getBusNumber(), seat.getStopIndex());

            return new SeatAvailabilityWithStopName(
                    seat.getStopIndex(),
                    stopName,  // Use the actual stop name fetched from BusStop entity
                    seat.getPercentage(),
                    lastUpdated
            );
        }).collect(Collectors.toList());
    }

    @Override
    public SeatAvailability saveAvailability(SeatAvailability seatAvailability) {
        seatAvailability.setLastUpdated(LocalDateTime.now());
        seatAvailability.setStopName(fetchStopName(seatAvailability.getBusNumber(), seatAvailability.getStopIndex()));  // Fetch actual stop name
        return repository.save(seatAvailability);  // For POST - Create new seat availability
    }

    @Override
    public SeatAvailability updateSeatAvailability(String busNumber, int stopIndex, int percentage) {
        Optional<SeatAvailability> existing = repository.findByBusNumberAndStopIndex(busNumber, stopIndex);

        if (existing.isPresent()) {
            SeatAvailability sa = existing.get();
            sa.setPercentage(percentage);
            sa.setStopName(fetchStopName(busNumber, stopIndex));  // Update stop name dynamically
            sa.setLastUpdated(LocalDateTime.now());  // Update timestamp
            return repository.save(sa);  // For PUT - Update existing seat availability
        } else {
            // If seat availability doesn't exist, create a new one
            return createSeatAvailability(busNumber, stopIndex, percentage);  // Calls POST logic
        }
    }

    private SeatAvailability createSeatAvailability(String busNumber, int stopIndex, int percentage) {
        SeatAvailability newSa = new SeatAvailability();
        newSa.setBusNumber(busNumber);
        newSa.setStopIndex(stopIndex);
        newSa.setPercentage(percentage);
        newSa.setStopName(fetchStopName(busNumber, stopIndex));  // Fetch actual stop name
        newSa.setLastUpdated(LocalDateTime.now());  // Set timestamp on creation
        return repository.save(newSa);  // Save new record
    }

    // Fetch stop name from the BusStop entity based on busNumber and stopIndex
    private String fetchStopName(String busNumber, int stopIndex) {
        Optional<BusStop> busStopOpt = busStopRepository.findByBusNumberAndIndex(busNumber, stopIndex);
        return busStopOpt.map(BusStop::getStopName).orElse("Unknown Stop");  // Default to "Unknown Stop" if not found
    }
}
