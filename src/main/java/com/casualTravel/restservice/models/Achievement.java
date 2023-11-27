package com.casualTravel.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Achievement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievementId;

    private String name;
    private String level;
    private String achievementPhotoUrl;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_achievementID",
            joinColumns = @JoinColumn(name = "achievementId" ,referencedColumnName = "achievementID"),
            inverseJoinColumns = @JoinColumn(name = "userId" , referencedColumnName = "userID")
    )
    @JsonIgnore
    private List<User> users;

    public Achievement(){
    }

    public Achievement(String name, String level, String achievementPhotoUrl) {
        this.name = name;
        this.level = level;
        this.achievementPhotoUrl = achievementPhotoUrl;
    }

    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAchievementPhotoUrl() {
        return achievementPhotoUrl;
    }

    public void setAchievementPhotoUrl(String achievementPhotoUrl) {
        this.achievementPhotoUrl = achievementPhotoUrl;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
