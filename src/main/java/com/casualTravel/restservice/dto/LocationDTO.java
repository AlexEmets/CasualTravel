package com.casualTravel.restservice.dto;

public class LocationDTO {
    private Double latitude;
    private Double longtitude;

    public LocationDTO(Double x, Double y) {
        this.latitude = x;
        this.longtitude = y;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double x) {
        this.latitude = x;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double y) {
        this.longtitude = y;
    }
}
