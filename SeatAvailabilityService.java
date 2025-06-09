package com.MainBus.Service;

import com.MainBus.DTO.SeatAvailabilityWithStopName;
import com.MainBus.Model.SeatAvailability;

import java.util.List;

public interface SeatAvailabilityService {
    // Update return type to be a list of SeatAvailabilityWithStopName DTOs
    List<SeatAvailabilityWithStopName> getAvailabilityByBusNumber(String busNumber);
    SeatAvailability saveAvailability(SeatAvailability seatAvailability);
    SeatAvailability updateSeatAvailability(String busNumber, int stopIndex, int percentage);
}

