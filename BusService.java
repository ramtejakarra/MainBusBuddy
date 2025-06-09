package com.MainBus.Service;

import com.MainBus.DTO.BusDTO;
import java.util.List;
import java.util.Optional;

public interface BusService {

    /**
     * Adds a new bus to the system.
     *
     * @param busDTO The bus details.
     */
    void addBus(BusDTO busDTO);

    /**
     * Searches for buses by exact start and destination points.
     *
     * @param startPoint Start location.
     * @param destinationPoint Destination location.
     * @return List of matching buses.
     */
    List<BusDTO> searchBuses(String startPoint, String destinationPoint);

    /**
     * Searches for buses where the given start and destination points are intermediate stops.
     *
     * @param startPoint Intermediate start stop.
     * @param destinationPoint Intermediate destination stop.
     * @return List of matching buses.
     */
    List<BusDTO> searchBusesByIntermediateStops(String startPoint, String destinationPoint);

    /**
     * Retrieves a bus by its bus number.
     *
     * @param busNumber The bus number.
     * @return Optional containing the bus details if found.
     */
    Optional<BusDTO> getBusByBusNumber(String busNumber);

    /**
     * Deletes a bus by its ID.
     *
     * @param id The ID of the bus to delete.
     */
    void deleteBus(Long id);

    /**
     * Updates an existing bus with new details.
     *
     * @param id ID of the bus to update.
     * @param busDTO New bus details.
     */
    void updateBus(Long id, BusDTO busDTO);
}
