package com.MainBus.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_requests")
public class EmergencyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobile;
    private String email;
    private String emergencyType;
    private LocalDateTime timestamp;

    // Constructors
    public EmergencyRequest() {
    }

    public EmergencyRequest(String name, String mobile, String email, String emergencyType, LocalDateTime timestamp) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.emergencyType = emergencyType;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
