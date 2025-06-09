package com.MainBus.Service;

import com.MainBus.Model.BusStop;
import com.MainBus.Repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStopService {
    @Autowired
    private BusStopRepository busStopRepository;

    public List<BusStop> getStopsByBusNumber(String busNumber) {
        return busStopRepository.findByBusNumberOrderByIndexAsc(busNumber);
    }
}
