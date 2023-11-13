package com.casualTravel.restservice.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Interest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestID;
    private String name;
    private String description;
    private String imageURL;
    private boolean type;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "interestId" ,referencedColumnName = "interestID"),
            inverseJoinColumns = @JoinColumn(name = "userId" , referencedColumnName = "userID")
    )
    @JsonIgnore
    private List<User> users;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "place_interest",
            joinColumns = @JoinColumn(name = "interestId" ,referencedColumnName = "interestID"),
            inverseJoinColumns = @JoinColumn(name = "placeId" , referencedColumnName = "placeID")
    )
    @JsonIgnore
    private List<Place> places;


    public Interest() {

    }

    public Interest(Long interestID, String name, String description) {
        this.interestID = interestID;
        this.name = name;
        this.description = description;
    }

    public Long getInterestID() {
        return interestID;
    }

    public void setInterestID(Long interestID) {
        this.interestID = interestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<User> getUsers() {
        return users;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
