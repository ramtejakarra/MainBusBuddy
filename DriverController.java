package com.MainBus.Controller;

import com.MainBus.DTO.DriverDTO;
import com.MainBus.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Save driver details
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody DriverDTO driverDTO) {
        driverService.saveDriver(driverDTO);
        return ResponseEntity.ok("Driver registered successfully!");
    }

    // Validate login credentials
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DriverDTO driverDTO) {
        boolean isValid = driverService.validateDriverCredentials(driverDTO.getPhoneNumber(), driverDTO.getBusNumber());
        if (isValid) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Fetch driver details
    @GetMapping("/details")
    public ResponseEntity<DriverDTO> getDriverDetails(
            @RequestParam String phoneNumber,
            @RequestParam String busNumber
    ) {
        DriverDTO driverDTO = driverService.getDriverDetails(phoneNumber, busNumber);
        return ResponseEntity.ok(driverDTO);
    }
}