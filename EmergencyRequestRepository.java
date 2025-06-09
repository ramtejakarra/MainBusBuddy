package com.MainBus.Repository;




import com.MainBus.Model.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, Long> {
}
