package com.casualTravel.restservice.dto;

import com.casualTravel.restservice.models.Place;

import java.util.List;

public class PlaceOut {

    private Long placeId;
    private Long googleId;
    private String nameForUser;
    private LocationDTO location;
    private int visitTime;
    private Float visitCost;
    private List<InterestDTO> categories;

    public PlaceOut(Long placeId, Long googleId, String nameForUser, LocationDTO location, int visitTime, Float visitCost, List<InterestDTO> categories) {
        this.placeId = placeId;
        this.googleId = googleId;
        this.nameForUser = nameForUser;
        this.location = location;
        this.visitTime = visitTime;
        this.visitCost = visitCost;
        this.categories = categories;
    }

    public static PlaceOut mapToPlaceResponseDTO(Place place) {
        LocationDTO location = new LocationDTO(place.getPositionX(), place.getPositionY());
        List<InterestDTO> interestDTOs = InterestDTO.getInterestsDTO(place.getInterests());

        return new PlaceOut(
                place.getPlaceID(),
                place.getGoogleID(),
                place.getPlaceName(),
                location,
                place.getVisitTime(),
                place.getVisitCost(),
                interestDTOs
        );
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Long googleId) {
        this.googleId = googleId;
    }

    public String getNameForUser() {
        return nameForUser;
    }

    public void setNameForUser(String nameForUser) {
        this.nameForUser = nameForUser;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public int getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(int visitTime) {
        this.visitTime = visitTime;
    }

    public Float getVisitCost() {
        return visitCost;
    }

    public void setVisitCost(Float visitCost) {
        this.visitCost = visitCost;
    }

    public List<InterestDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<InterestDTO> categories) {
        this.categories = categories;
    }
}
