package com.casualTravel.restservice.models;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Float childCost;
    private Float preferentialCost;
    private Boolean sports;
    private Boolean overview;
    private Boolean passiveAction;
    private Boolean educationalAction;
    private Boolean outdoorAction;
    private Boolean invalid;
    private Boolean family;
    private Boolean mustVisit;
    private Boolean museum;
    private Boolean artCenter;
    private Boolean religionObject;
    private Boolean architecturalMonument;
    private Boolean sculptur;
    private Boolean park;
    private Boolean naturalNationObject;
    private Boolean activeRecreationArea;
    private Boolean alley;
    private boolean observationDeck;




//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "place_interest",
//            joinColumns = @JoinColumn(name = "placeId", referencedColumnName = "placeID"),

    @Override
    public String toString() {
        return "Point{" +
            "placeID=" + placeID +
            ", googleID=" + googleID +
            ", placeName='" + placeName + '\'' +
            ", positionX='" + positionX + '\'' +
            ", positionY='" + positionY + '\'' +
            ", visitTime=" + visitTime +
            ", visitCost=" + visitCost +
            ", childCost=" + childCost +
            ", preferentialCost=" + preferentialCost +
            ", sports=" + sports +
            ", overview=" + overview +
            ", passiveAction=" + passiveAction +
            ", educationalAction=" + educationalAction +
            ", outdoorAction=" + outdoorAction +
            ", invalid=" + invalid +
            ", family=" + family +
            ", mustVisit=" + mustVisit +
            ", museum=" + museum +
            ", artCenter=" + artCenter +
            ", religionObject=" + religionObject +
            ", architecturalMonument=" + architecturalMonument +
            ", sculptur=" + sculptur +
            ", park=" + park +
            ", naturalNationObject=" + naturalNationObject +
            ", activeRecreationArea=" + activeRecreationArea +
            ", alley=" + alley +
            ", observationDeck=" + observationDeck +
            ", interests=" + interests +
            ", userPlaces=" + userPlaces +
            '}';
    }
//            inverseJoinColumns = @JoinColumn(name = "interestId", referencedColumnName = "interestID")
//    )
//    private List<Interest> interests;

    @ElementCollection
    @CollectionTable(name="place_interest", joinColumns = @JoinColumn(name = "placeId"))
    @MapKeyJoinColumn(name = "interestId")
    @Column(name = "interest_weight")
    private Map<Interest, Double> interests = new HashMap<>();

    @OneToMany(mappedBy = "place")
    private List<UserPlace> userPlaces;


    public Place() {

    }

    public Place(Long placeID, Long googleID, String placeName, String positionX, String positionY,
        int visitTime, Float visitCost, Float childCost, Float preferentialCost, Boolean sports,
        Boolean overview, Boolean passiveAction, Boolean educationalAction, Boolean outdoorAction,
        Boolean invalid, Boolean family, Boolean mustVisit, Boolean museum, Boolean artCenter,
        Boolean religionObject, Boolean architecturalMonument, Boolean sculptur, Boolean park,
        Boolean naturalNationObject, Boolean activeRecreationArea, Boolean alley,
        boolean observationDeck, Map<Interest, Double> interests, List<UserPlace> userPlaces) {
        this.placeID = placeID;
        this.googleID = googleID;
        this.placeName = placeName;
        this.positionX = positionX;
        this.positionY = positionY;
        this.visitTime = visitTime;
        this.visitCost = visitCost;
        this.childCost = childCost;
        this.preferentialCost = preferentialCost;
        this.sports = sports;
        this.overview = overview;
        this.passiveAction = passiveAction;
        this.educationalAction = educationalAction;
        this.outdoorAction = outdoorAction;
        this.invalid = invalid;
        this.family = family;
        this.mustVisit = mustVisit;
        this.museum = museum;
        this.artCenter = artCenter;
        this.religionObject = religionObject;
        this.architecturalMonument = architecturalMonument;
        this.sculptur = sculptur;
        this.park = park;
        this.naturalNationObject = naturalNationObject;
        this.activeRecreationArea = activeRecreationArea;
        this.alley = alley;
        this.observationDeck = observationDeck;
        this.interests = interests;
        this.userPlaces = userPlaces;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
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

    public Float getChildCost() {
        return childCost;
    }

    public void setChildCost(Float childCost) {
        this.childCost = childCost;
    }

    public Float getPreferentialCost() {
        return preferentialCost;
    }

    public void setPreferentialCost(Float preferentialCost) {
        this.preferentialCost = preferentialCost;
    }

    public Boolean getSports() {
        return sports;
    }

    public void setSports(Boolean sports) {
        this.sports = sports;
    }

    public Boolean getOverview() {
        return overview;
    }

    public void setOverview(Boolean overview) {
        this.overview = overview;
    }

    public Boolean getPassiveAction() {
        return passiveAction;
    }

    public void setPassiveAction(Boolean passiveAction) {
        this.passiveAction = passiveAction;
    }

    public Boolean getEducationalAction() {
        return educationalAction;
    }

    public void setEducationalAction(Boolean educationalAction) {
        this.educationalAction = educationalAction;
    }

    public Boolean getOutdoorAction() {
        return outdoorAction;
    }

    public void setOutdoorAction(Boolean outdoorAction) {
        this.outdoorAction = outdoorAction;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public Boolean getFamily() {
        return family;
    }

    public void setFamily(Boolean family) {
        this.family = family;
    }

    public Boolean getMustVisit() {
        return mustVisit;
    }

    public void setMustVisit(Boolean mustVisit) {
        this.mustVisit = mustVisit;
    }

    public Boolean getMuseum() {
        return museum;
    }

    public void setMuseum(Boolean museum) {
        this.museum = museum;
    }

    public Boolean getArtCenter() {
        return artCenter;
    }

    public void setArtCenter(Boolean artCenter) {
        this.artCenter = artCenter;
    }

    public Boolean getReligionObject() {
        return religionObject;
    }

    public void setReligionObject(Boolean religionObject) {
        this.religionObject = religionObject;
    }

    public Boolean getArchitecturalMonument() {
        return architecturalMonument;
    }

    public void setArchitecturalMonument(Boolean architecturalMonument) {
        this.architecturalMonument = architecturalMonument;
    }

    public Boolean getSculptur() {
        return sculptur;
    }

    public void setSculptur(Boolean sculptur) {
        this.sculptur = sculptur;
    }

    public Boolean getPark() {
        return park;
    }

    public void setPark(Boolean park) {
        this.park = park;
    }

    public Boolean getNaturalNationObject() {
        return naturalNationObject;
    }

    public void setNaturalNationObject(Boolean naturalNationObject) {
        this.naturalNationObject = naturalNationObject;
    }

    public Boolean getActiveRecreationArea() {
        return activeRecreationArea;
    }

    public void setActiveRecreationArea(Boolean activeRecreationArea) {
        this.activeRecreationArea = activeRecreationArea;
    }

    public Boolean getAlley() {
        return alley;
    }

    public void setAlley(Boolean alley) {
        this.alley = alley;
    }

    public boolean isObservationDeck() {
        return observationDeck;
    }

    public void setObservationDeck(boolean observationDeck) {
        this.observationDeck = observationDeck;
    }

    public Map<Interest, Double> getInterests() {
        return interests;
    }

    public void setInterests(
        Map<Interest, Double> interests) {
        this.interests = interests;
    }
    public void addInterest(Interest interest, Double weight) {
        interests.put(interest, weight);
    }
    public List<UserPlace> getUserPlaces() {
        return userPlaces;
    }

    public void setUserPlaces(List<UserPlace> userPlaces) {
        this.userPlaces = userPlaces;
    }
}
