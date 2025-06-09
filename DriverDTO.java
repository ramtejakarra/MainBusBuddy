package com.MainBus.DTO;

public class DriverDTO {
    private String driverName;
    private String phoneNumber;
    private String busNumber;
    private String startPoint;
    private String destinationPoint;

    // Constructor
    public DriverDTO(String driverName, String phoneNumber, String busNumber, String startPoint, String destinationPoint) {
        this.driverName = driverName;
        this.phoneNumber = phoneNumber;
        this.busNumber = busNumber;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }

    // Getters and Setters
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }
}