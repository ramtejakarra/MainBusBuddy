package com.MainBus.Service;

import com.MainBus.Model.EmergencyRequest;
import com.MainBus.Repository.EmergencyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmergencyRequestService {

    private final EmergencyRequestRepository emergencyRequestRepository;

    @Autowired
    public EmergencyRequestService(EmergencyRequestRepository emergencyRequestRepository) {
        this.emergencyRequestRepository = emergencyRequestRepository;
    }

    public EmergencyRequest saveEmergencyRequest(EmergencyRequest request) {
        // Optional: validate input or add additional logic
        request.setTimestamp(LocalDateTime.now());
        return emergencyRequestRepository.save(request);
    }
}

