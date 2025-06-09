package com.MainBus.Repository;

import com.MainBus.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByPhoneNumberAndBusNumber(String phoneNumber, String busNumber);

    Driver findByPhoneNumberAndBusNumber(String phoneNumber, String busNumber);
}
