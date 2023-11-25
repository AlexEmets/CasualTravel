package com.casualTravel.restservice.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeID;
    private Long googleID;
    private String placeName;
    private String positionX;
    private String positionY;
    private int visitTime;
    private Float visitCost;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "place_interest",
            joinColumns = @JoinColumn(name = "placeId", referencedColumnName = "placeID"),
            inverseJoinColumns = @JoinColumn(name = "interestId", referencedColumnName = "interestID")
    )
    private List<Interest> interests;

    @OneToMany(mappedBy = "place")
    private List<UserPlace> userPlaces;


    public Place() {

    }

    public Place(Long placeID, Long googleID, String positionX, String positionY, List<Interest> interests) {
        this.placeID = placeID;
        this.googleID = googleID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.interests = interests;
    }

    public Long getPlaceID() {
        return placeID;
    }

    public void setPlaceID(Long placeID) {
        this.placeID = placeID;
    }

    public Long getGoogleID() {
        return googleID;
    }

    public void setGoogleID(Long googleID) {
        this.googleID = googleID;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getGetPositionY() {
        return positionY;
    }

    public void setGetPositionY(String getPositionY) {
        this.positionY = getPositionY;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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

    public List<UserPlace> getUserPlaces() {
        return userPlaces;
    }

    public void setUserPlaces(List<UserPlace> userPlaces) {
        this.userPlaces = userPlaces;
    }
}
