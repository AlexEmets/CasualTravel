package com.example.restservice.models;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyID;
    @OneToMany(mappedBy = "surveyID")
    private List<Question> questions;
    public Survey() {
    }

    public Survey(Long surveyID, List<Question> questions) {
        this.surveyID = surveyID;
        this.questions = questions;
    }

    public Long getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Long surveyID) {
        this.surveyID = surveyID;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
