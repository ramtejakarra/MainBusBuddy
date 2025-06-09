package com.MainBus.Model;



import jakarta.persistence.*;

@Entity
@Table(name = "favorite_buses")
public class FavoriteBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Assuming user management is implemented

    private String busId;
    private String busNumber;
    private String departure;
    private String stops; // Comma-separated list
    private String type;
    private String duration;

    // Constructors
    public FavoriteBus() {}

    public FavoriteBus(Long userId, String busId, String busNumber, String departure, String stops, String type, String duration) {
        this.userId = userId;
        this.busId = busId;
        this.busNumber = busNumber;
        this.departure = departure;
        this.stops = stops;
        this.type = type;
        this.duration = duration;
    }

    // Getters and Setters

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}

