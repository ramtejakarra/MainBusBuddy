package com.MainBus.DTO;

/**
 * DTO representing seat availability information at a specific stop along with the last updated timestamp.
 */
public class SeatAvailabilityWithStopName {
    private int stopIndex;
    private String stopName;
    private int percentage;
    private String lastUpdated;

    public SeatAvailabilityWithStopName() {
    }

    public SeatAvailabilityWithStopName(int stopIndex, String stopName, int percentage, String lastUpdated) {
        this.stopIndex = stopIndex;
        this.stopName = stopName;
        this.percentage = percentage;
        this.lastUpdated = lastUpdated;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public void setStopIndex(int stopIndex) {
        this.stopIndex = stopIndex;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
