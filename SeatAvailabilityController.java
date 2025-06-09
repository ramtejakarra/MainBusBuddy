package com.MainBus.Controller;

import com.MainBus.DTO.SeatAvailabilityWithStopName;
import com.MainBus.Model.SeatAvailability;
import com.MainBus.Service.SeatAvailabilityService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/seat-availability")
@CrossOrigin(origins = "*")
public class SeatAvailabilityController {

    private final SeatAvailabilityService service;

    public SeatAvailabilityController(SeatAvailabilityService service) {
        this.service = service;
    }

    /**
     * Endpoint to get seat availability for a given bus number.
     * This returns a list of SeatAvailabilityWithStopName DTOs.
     */
    @GetMapping("/{busNumber}")
    public List<SeatAvailabilityWithStopName> getAvailability(@PathVariable String busNumber) {
        return service.getAvailabilityByBusNumber(busNumber);  // Ensure this returns the DTO list
    }

    /**
     * Endpoint to save seat availability data.
     * This method now also updates the 'lastUpdated' field automatically in the service layer.
     */
    @PostMapping
    public ResponseEntity<SeatAvailability> saveAvailability(@RequestBody SeatAvailability seatAvailability) {
        SeatAvailability saved = service.saveAvailability(seatAvailability);
        return ResponseEntity.ok(saved);
    }

    /**
     * Endpoint to update seat availability.
     * Updates seat availability and also the 'lastUpdated' field.
     */
    @PutMapping("/update")
    public ResponseEntity<SeatAvailability> updateSeatAvailability(@RequestBody SeatAvailability seatAvailability) {
        SeatAvailability updated = service.updateSeatAvailability(
                seatAvailability.getBusNumber(),
                seatAvailability.getStopIndex(),
                seatAvailability.getPercentage()
        );
        return ResponseEntity.ok(updated);
    }
}
