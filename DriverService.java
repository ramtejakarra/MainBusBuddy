package com.MainBus.Service;

import com.MainBus.DTO.DriverDTO;
import com.MainBus.Model.Driver;
import com.MainBus.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // Save driver details to the database
    public void saveDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setDriverName(driverDTO.getDriverName());
        driver.setPhoneNumber(driverDTO.getPhoneNumber());
        driver.setBusNumber(driverDTO.getBusNumber());
        driver.setStartPoint(driverDTO.getStartPoint());
        driver.setDestinationPoint(driverDTO.getDestinationPoint());

        driverRepository.save(driver);
    }

    // Validate driver credentials (phone number and bus number)
    public boolean validateDriverCredentials(String phoneNumber, String busNumber) {
        return driverRepository.existsByPhoneNumberAndBusNumber(phoneNumber, busNumber);
    }

    // Fetch driver details by phone number and bus number
    public DriverDTO getDriverDetails(String phoneNumber, String busNumber) {
        Driver driver = driverRepository.findByPhoneNumberAndBusNumber(phoneNumber, busNumber);
        if (driver == null) {
            throw new RuntimeException("Driver not found");
        }
        return new DriverDTO(
                driver.getDriverName(),
                driver.getBusNumber(),
                driver.getPhoneNumber(),
                driver.getStartPoint(),
                driver.getDestinationPoint()
        );
    }
}