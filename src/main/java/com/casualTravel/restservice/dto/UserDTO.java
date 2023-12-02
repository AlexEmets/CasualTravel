package com.casualTravel.restservice.dto;

import com.casualTravel.restservice.models.Achievement;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.models.UserPlace;

import java.util.List;
import java.util.Map;

public class UserDTO {
    private Long userID;
    private String username;
    private String email;
    private String avatar;
    private Integer betterThenPercentages;
    private List<InterestDTO> interests;

    private List<Achievement> achievements;
    private List<UserPlace> userPlaces;

    public UserDTO(Long userID, String username, String email, String avatar, Integer betterThenPercentages, List<InterestDTO> interests, List<Achievement> achievements, List<UserPlace> userPlaces) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.betterThenPercentages = betterThenPercentages;
        this.interests = interests;
        this.achievements = achievements;
        this.userPlaces = userPlaces;
    }

    public static UserDTO mapToUserDTO(User user) {
        List<InterestDTO> interestDTOs = InterestDTO.getInterestsDTO(user.getInterests());

        return new UserDTO(
                user.getUserID(),
                user.getUserNameName(),
                user.getEmail(),
                user.getAvatar(),
                user.getBetterThenPercentages(),
                interestDTOs,
                user.getAchievements(),
                user.getUserPlaces()
        );
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

    public List<InterestDTO> getInterests() {
        return interests;
    }

    public void setInterests(List<InterestDTO> interests) {
        this.interests = interests;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<UserPlace> getUserPlaces() {
        return userPlaces;
    }

    public void setUserPlaces(List<UserPlace> userPlaces) {
        this.userPlaces = userPlaces;
    }
}
