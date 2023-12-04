package com.casualTravel.restservice.dto;

public class LocationDTO {
    private Double latitude;
    private Double longitude;

    public LocationDTO(Double x, Double y) {
        this.latitude = x;
        this.longitude = y;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double x) {
        this.latitude = x;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double y) {
        this.longitude = y;
    }
}
