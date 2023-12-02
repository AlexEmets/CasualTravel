package com.casualTravel.restservice.dto;

import com.casualTravel.restservice.models.Interest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterestDTO {
    private Long interestId;
    private String interestName;
    private String imgUrl;
    private Double wage;

    public static List<InterestDTO> getInterestsDTO(Map<Interest, Double> interests) {
        List<InterestDTO> interestDTOList = new ArrayList<>();

        for (Map.Entry<Interest, Double> entry : interests.entrySet()) {
            Interest interest = entry.getKey();
            InterestDTO interestDTO = InterestDTO.mapToInterestDTO(interest);
            interestDTO.setWage(entry.getValue());
            interestDTOList.add(interestDTO);
        }
        return interestDTOList;
    }

    public static InterestDTO mapToInterestDTO(Interest interest) {
        return new InterestDTO(
                interest.getInterestID(),
                interest.getName(),
                interest.getImageURL()
        );
    }

    public InterestDTO(Long interestId, String interestName, String imgUrl) {
        this.interestId = interestId;
        this.interestName = interestName;
        this.imgUrl = imgUrl;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }
}
