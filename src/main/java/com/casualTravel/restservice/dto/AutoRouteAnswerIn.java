package com.casualTravel.restservice.dto;

public class AutoRouteAnswerIn {
    private Integer locationsCount;

    private Integer priceLevel;

    private Boolean mustVisitWeight;

    private String restPreffered;
        //(sports, educationalAction, passiveAction)

    private Boolean outdoorAction;

    private LocationDTO startLocation;

    private LocationDTO endLocation;

    public Integer getLocationsCount() {
        return locationsCount;
    }

    public void setLocationsCount(Integer locationsCount) {
        this.locationsCount = locationsCount;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Boolean getMustVisitWeight() {
        return mustVisitWeight;
    }

    public void setMustVisitWeight(Boolean mustVisitWeight) {
        this.mustVisitWeight = mustVisitWeight;
    }

    public String getRestPreffered() {
        return restPreffered;
    }

    public void setRestPreffered(String restPreffered) {
        this.restPreffered = restPreffered;
    }

    public Boolean getOutdoorAction() {
        return outdoorAction;
    }

    public void setOutdoorAction(Boolean outdoorAction) {
        this.outdoorAction = outdoorAction;
    }

    public LocationDTO getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationDTO startLocation) {
        this.startLocation = startLocation;
    }

    public LocationDTO getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationDTO endLocation) {
        this.endLocation = endLocation;
    }
}
