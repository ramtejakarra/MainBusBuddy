package com.MainBus.implementation;

import com.MainBus.DTO.BusDTO;
import com.MainBus.Model.Bus;
import com.MainBus.Repository.BusRepository;
import com.MainBus.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public void addBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setType(busDTO.getType());
        bus.setDepartureTime(busDTO.getDepartureTime());

        // Normalize start and destination points
        bus.setStartPoint(busDTO.getStartPoint().toLowerCase());
        bus.setDestinationPoint(busDTO.getDestinationPoint().toLowerCase());

        // Normalize stops
        List<String> normalizedStops = busDTO.getStops().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        bus.setStops(normalizedStops);

        bus.setDuration(busDTO.getDuration());

        busRepository.save(bus);
    }

    @Override
    public List<BusDTO> searchBuses(String startPoint, String destinationPoint) {
        return busRepository.findByStartPointAndDestinationPoint(
                        startPoint.toLowerCase(),
                        destinationPoint.toLowerCase()
                )
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusDTO> searchBusesByIntermediateStops(String startPoint, String destinationPoint) {
        List<Bus> allBuses = busRepository.findAll();

        return allBuses.stream()
                .filter(bus -> {
                    List<String> stops = bus.getStops(); // Using stops directly from Bus entity
                    List<String> normalizedStops = stops.stream()
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());

                    int startIndex = normalizedStops.indexOf(startPoint.toLowerCase());
                    int endIndex = normalizedStops.indexOf(destinationPoint.toLowerCase());

                    return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
                })
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusDTO> getBusByBusNumber(String busNumber) {
        Optional<Bus> busOptional = busRepository.findByBusNumber(busNumber);
        return busOptional.map(this::convertToDTO);
    }

    @Override
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    @Override
    public void updateBus(Long id, BusDTO busDTO) {
        Optional<Bus> optionalBus = busRepository.findById(id);
        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            bus.setBusNumber(busDTO.getBusNumber());
            bus.setType(busDTO.getType());
            bus.setDepartureTime(busDTO.getDepartureTime());

            // Normalize start and destination points
            bus.setStartPoint(busDTO.getStartPoint().toLowerCase());
            bus.setDestinationPoint(busDTO.getDestinationPoint().toLowerCase());

            // Normalize stops
            List<String> normalizedStops = busDTO.getStops().stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            bus.setStops(normalizedStops);

            bus.setDuration(busDTO.getDuration());

            busRepository.save(bus);
        } else {
            throw new RuntimeException("Bus not found with ID: " + id);
        }
    }

    private BusDTO convertToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setBusNumber(bus.getBusNumber());
        busDTO.setType(bus.getType());
        busDTO.setDepartureTime(bus.getDepartureTime());
        busDTO.setStartPoint(bus.getStartPoint());
        busDTO.setDestinationPoint(bus.getDestinationPoint());
        busDTO.setStops(bus.getStops());
        busDTO.setDuration(bus.getDuration());
        return busDTO;
    }
}
