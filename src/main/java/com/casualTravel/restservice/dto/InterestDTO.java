package com.casualTravel.restservice.dto;

public class InterestDTO {
    private Long interestId;
    private String interestName;
    private String imgUrl;

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
}
