package com.example.restservice.models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyID;

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
