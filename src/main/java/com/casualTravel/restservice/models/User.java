package com.casualTravel.restservice.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;


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

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "user_interest",
//            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userID"),
//            inverseJoinColumns = @JoinColumn(name = "interestId", referencedColumnName = "interestID")
//    )
//    private List<Interest> interests;

    @ElementCollection
    @CollectionTable(name="user_interest", joinColumns = @JoinColumn(name = "userId"))
    @MapKeyJoinColumn(name = "interestId")
    @Column(name = "interest_weight")
    private Map<Interest, Double> interests = new HashMap<>();

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

    public User(Long userID, String username,  String email, String password, String avatar) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return email;
    }

    public String getUserNameName() {
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

    public Integer getBetterThenPercentages() {
        return betterThenPercentages;
    }

    public void setBetterThenPercentages(Integer betterThenPercentages) {
        this.betterThenPercentages = betterThenPercentages;
    }

    public List<UserPlace> getUserPlaces() {
        return userPlaces;
    }

    public void setUserPlaces(List<UserPlace> userPlaces) {
        this.userPlaces = userPlaces;
    }

    public Map<Interest, Double> getInterests() {
        return interests;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void setInterests(Map<Interest, Double> interests) {
        this.interests = interests;
    }

    public void addInterest(Interest interest) {
//        if (interests == null) {
//            interests = new HashMap<>();
//        }
//        if (!interests.contains(interest)) {
//            interests.add(interest);
//        }
    }

    public void removeInterest(Interest interest) {
//        if (interests != null && interests.contains(interest)) {
//            interests.remove(interest);
//        }
    }

    public void addAchievement(Achievement achievement) {
        if (achievements == null) {
            achievements = new ArrayList<>();
        }
        if (!achievements.contains(achievement)) {
            achievements.add(achievement);
        }
    }

    public void removeAchievement(Achievement achievement) {
        if (achievements != null && achievements.contains(achievement)) {
            achievements.remove(achievement);
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