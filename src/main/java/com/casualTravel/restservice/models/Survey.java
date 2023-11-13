package com.casualTravel.restservice.models;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table
public class Survey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyID;
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public Survey() {
    }

    public Survey(Long surveyID, List<Question> questions) {
        this.surveyID = surveyID;

    }
    public Long getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Long surveyID) {
        this.surveyID = surveyID;
    }
}
