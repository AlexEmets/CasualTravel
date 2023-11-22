package com.casualTravel.restservice.models;

import jakarta.persistence.*;

import java.util.List;
import javax.xml.crypto.Data;

@Entity
@Table
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeID;
    private Long googleID;
    private String positionX;
    private String getPositionY;
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

    public Place(Long placeID, Long googleID, String positionX, String getPositionY, List<Interest> interests) {
        this.placeID = placeID;
        this.googleID = googleID;
        this.positionX = positionX;
        this.getPositionY = getPositionY;
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
        return getPositionY;
    }

    public void setGetPositionY(String getPositionY) {
        this.getPositionY = getPositionY;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }


}
