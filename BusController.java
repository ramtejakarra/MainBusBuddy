package com.MainBus.Controller;

import com.MainBus.DTO.BusDTO;
import com.MainBus.Model.Bus;
import com.MainBus.Model.BusStop;
import com.MainBus.Model.SeatAvailability;
import com.MainBus.Repository.BusRepository;
import com.MainBus.Repository.BusStopRepository;
import com.MainBus.Repository.SeatAvailabilityRepository;
import com.MainBus.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private SeatAvailabilityRepository seatAvailabilityRepository;

    @Autowired
    private BusRepository busRepository; // ✅ Added to support suggestions

    @PostMapping("/add")
    public ResponseEntity<String> addBus(@RequestBody BusDTO busDTO) {
        busService.addBus(busDTO);
        return ResponseEntity.ok("Bus added successfully!");
    }

    @GetMapping("/search")
    public ResponseEntity<List<BusDTO>> searchBuses(
            @RequestParam String startPoint,
            @RequestParam String destinationPoint) {
        return ResponseEntity.ok(busService.searchBuses(startPoint, destinationPoint));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.ok("Bus deleted successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBus(@PathVariable Long id, @RequestBody BusDTO busDTO) {
        busService.updateBus(id, busDTO);
        return ResponseEntity.ok("Bus updated successfully!");
    }

    @GetMapping("/{busNumber}/stops")
    public ResponseEntity<List<BusStop>> getBusStops(@PathVariable String busNumber) {
        List<BusStop> stops = busStopRepository.findByBusNumber(busNumber);
        return ResponseEntity.ok(stops);
    }

    @PostMapping("/{busNumber}/stops/add")
    public ResponseEntity<String> addBusStop(
            @PathVariable String busNumber,
            @RequestBody BusStop busStop) {

        busStop.setBusNumber(busNumber);

        // Set default seat availability if not provided
        if (busStop.getSeatAvailability() == 0) {
            busStop.setSeatAvailability(100);
        }

        // Set completed status to false by default if not explicitly set
        if (!busStop.isCompleted()) {
            busStop.setCompleted(false);
        }

        busStopRepository.save(busStop);
        return ResponseEntity.ok("Bus stop added successfully!");
    }

    @PostMapping("/{busNumber}/stop/{stopIndex}/availability")
    public ResponseEntity<String> updateSeatAvailability(
            @PathVariable String busNumber,
            @PathVariable int stopIndex,
            @RequestParam int percentage) {
        seatAvailabilityRepository.findByBusNumberAndStopIndex(busNumber, stopIndex)
                .ifPresent(seat -> {
                    seat.setPercentage(percentage);
                    seatAvailabilityRepository.save(seat);
                });

        // Also update in BusStop table
        busStopRepository.findByBusNumber(busNumber).stream()
                .filter(stop -> stop.getIndex() == stopIndex)
                .findFirst()
                .ifPresent(stop -> {
                    stop.setSeatAvailability(percentage);
                    busStopRepository.save(stop);
                });

        return ResponseEntity.ok("Seat availability updated successfully!");
    }

    @GetMapping("/busNumber/{busNumber}")
    public ResponseEntity<BusDTO> getBusByBusNumber(@PathVariable String busNumber) {
        Optional<BusDTO> busDTO = busService.getBusByBusNumber(busNumber);
        if (busDTO.isPresent()) {
            return ResponseEntity.ok(busDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{busNumber}/stop/{stopIndex}/complete")
    public ResponseEntity<String> completeStop(
            @PathVariable String busNumber,
            @PathVariable int stopIndex) {
        busStopRepository.findByBusNumber(busNumber).stream()
                .filter(stop -> stop.getIndex() == stopIndex)
                .findFirst()
                .ifPresent(stop -> {
                    stop.setCompleted(true);
                    stop.setSeatAvailability(0); // reset availability on completion
                    busStopRepository.save(stop);
                });

        seatAvailabilityRepository.findByBusNumber(busNumber).stream()
                .filter(seat -> seat.getStopIndex() == stopIndex)
                .forEach(seat -> {
                    seat.setPercentage(0);
                    seatAvailabilityRepository.save(seat);
                });

        return ResponseEntity.ok("Stop completed and seat availability reset!");
    }

    @GetMapping("/search/intermediate")
    public ResponseEntity<List<BusDTO>> searchBusesByIntermediateStops(
            @RequestParam String startPoint,
            @RequestParam String destinationPoint) {
        List<BusDTO> buses = busService.searchBusesByIntermediateStops(startPoint, destinationPoint);
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/stops/all")
    public ResponseEntity<List<String>> getAllUniqueStops() {
        List<String> stops = busStopRepository.findAll()
                .stream()
                .map(BusStop::getStopName)
                .distinct()
                .toList();
        return ResponseEntity.ok(stops);
    }

    // ✅ New API: Suggest buses based on starting characters (query)
    @GetMapping("/suggest")
    public ResponseEntity<List<String>> suggestBuses(@RequestParam String query) {
        List<String> suggestions = busRepository.findByBusNumberStartingWithIgnoreCase(query)
                .stream()
                .map(Bus::getBusNumber)
                .toList();
        return ResponseEntity.ok(suggestions);
    }
}
