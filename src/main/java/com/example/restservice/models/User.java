package com.example.restservice.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;
    private String password;
    private String email;
    private String avatar;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "interestID")
    )
    private List<Interest> interests;

    public User() {

    }

    public User(Long userID, String username, String password, String email, String avatar, List<Interest> interests) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.interests = interests;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
    public void addInterest(Interest interest) {
        if (interests == null) {
            interests = new ArrayList<>();
        }
        if (!interests.contains(interest)) {
            interests.add(interest);
        }
    }

    public void removeInterest(Interest interest) {
        if (interests != null && interests.contains(interest)) {
            interests.remove(interest);
        }
    }
}