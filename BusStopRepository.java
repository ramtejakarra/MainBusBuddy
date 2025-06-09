package com.MainBus.Repository;

import com.MainBus.Model.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    /**
     * Fetch all bus stops for a specific bus number.
     */
    List<BusStop> findByBusNumber(String busNumber);

    /**
     * Fetch all bus stops for a specific bus number in order of stop index.
     */
    List<BusStop> findByBusNumberOrderByIndexAsc(String busNumber);

    /**
     * Fetch a specific bus stop by bus number and stop index.
     */
    Optional<BusStop> findByBusNumberAndIndex(String busNumber, int index);

    /**
     * Fetch all completed stops for a specific bus.
     */
    List<BusStop> findByBusNumberAndCompleted(String busNumber, boolean completed);

    /**
     * Fetch all incomplete stops for a specific bus.
     */
    @Query("SELECT b FROM BusStop b WHERE b.busNumber = :busNumber AND b.completed = false")
    List<BusStop> findIncompleteStopsByBusNumber(@Param("busNumber") String busNumber);
}
