package com.MainBus.Repository;

import com.MainBus.Model.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Long> {
    List<SeatAvailability> findByBusNumber(String busNumber);

    Optional<SeatAvailability> findByBusNumberAndStopIndex(String busNumber, int stopIndex);
}