package com.MainBus.Controller;

import com.MainBus.Model.BusStop;
import com.MainBus.Repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/busstops")
public class BusStopController {

    @Autowired
    private BusStopRepository busStopRepository;

    // POST endpoint to add stops for a bus
    @PostMapping("/{busNumber}")
    public ResponseEntity<String> addBusStops(
            @PathVariable String busNumber,
            @RequestBody List<BusStop> stops
    ) {
        for (BusStop stop : stops) {
            stop.setBusNumber(busNumber);
            stop.setCompleted(false); // default value
            busStopRepository.save(stop);
        }
        return ResponseEntity.ok("Bus stops added successfully for bus: " + busNumber);
    }

    // ✅ GET endpoint to fetch stop names for a busNumber
    @GetMapping("/{busNumber}")
    public ResponseEntity<List<String>> getBusStopsByBusNumber(@PathVariable String busNumber) {
        List<BusStop> stops = busStopRepository.findByBusNumber(busNumber);
        List<String> stopNames = stops.stream()
                .map(BusStop::getStopName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(stopNames);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBusStop(
            @PathVariable Long id,
            @RequestBody BusStop updatedStop) {

        return busStopRepository.findById(id)
                .map(existingStop -> {
                    existingStop.setStopName(updatedStop.getStopName());
                    existingStop.setCompleted(updatedStop.isCompleted()); // Optional
                    busStopRepository.save(existingStop);
                    return ResponseEntity.ok("Bus stop updated successfully.");
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBusStop(@PathVariable Long id) {
        if (busStopRepository.existsById(id)) {
            busStopRepository.deleteById(id);
            return ResponseEntity.ok("Bus stop deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
