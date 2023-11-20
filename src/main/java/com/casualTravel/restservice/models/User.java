package com.casualTravel.restservice.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "app_user")
@Builder
@AllArgsConstructor
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private Integer betterThenPercentages;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "interestId", referencedColumnName = "interestID")
    )
    private List<Interest> interests;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_achievementID",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "achievementId", referencedColumnName = "achievementId")
    )
    private List<Achievement> achievements;
    @OneToMany(mappedBy = "user")
    private List<UserPlace> userPlaces;

    public User() {

    }

    public User(Long userID, String username,  String email, String password, String avatar, List<Interest> interests, List<Achievement> achievements) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.interests = interests;
        this.achievements = achievements;
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

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
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
    public void addAchievement(Achievement interest) {
        if (achievements == null) {
            achievements = new ArrayList<>();
        }
        if (!achievements.contains(interest)) {
            achievements.add(interest);
        }
    }

    public void removeAchievement(Achievement interest) {
        if (achievements != null && achievements.contains(interest)) {
            achievements.remove(interest);
        }
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}