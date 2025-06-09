package com.MainBus.Repository;

import com.MainBus.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findByBusNumberStartingWithIgnoreCase(String busNumberPrefix);
    /**
     * Finds buses by exact start and destination points (case-insensitive).
     *
     * @param startPoint       Start location of the bus.
     * @param destinationPoint Destination location of the bus.
     * @return List of buses matching the start and destination points.
     */
    @Query("SELECT b FROM Bus b WHERE LOWER(b.startPoint) = LOWER(:startPoint) AND LOWER(b.destinationPoint) = LOWER(:destinationPoint)")
    List<Bus> findByStartPointAndDestinationPoint(
            @Param("startPoint") String startPoint,
            @Param("destinationPoint") String destinationPoint
    );

    /**
     * Finds a bus by its bus number (case-insensitive).
     *
     * @param busNumber Bus number to search for.
     * @return Optional containing the bus if found, otherwise empty.
     */
    @Query("SELECT b FROM Bus b WHERE LOWER(b.busNumber) = LOWER(:busNumber)")
    Optional<Bus> findByBusNumber(
            @Param("busNumber") String busNumber
    );

    // 🔥 Future Expansion (Example):
    // List<Bus> findByStartPointContainingIgnoreCase(String keyword);
    // List<Bus> findByDestinationPointContainingIgnoreCase(String keyword);
}
