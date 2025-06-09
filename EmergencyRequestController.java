package com.MainBus.Controller;




import com.MainBus.Model.EmergencyRequest;
import com.MainBus.Repository.EmergencyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/emergency")
public class EmergencyRequestController {

    @Autowired
    private EmergencyRequestRepository emergencyRequestRepository;

    @PostMapping("/submit")
    public EmergencyRequest submitEmergency(@RequestBody EmergencyRequest request) {
        return emergencyRequestRepository.save(request);
    }
}

