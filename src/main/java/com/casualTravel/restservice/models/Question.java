package com.casualTravel.restservice.models;
import jakarta.persistence.*;
import java.io.Serializable;


import java.util.Map;
import java.util.Set;

@Entity
@Table
public class Question implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;
    private String text;

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", text='" + text + '\'' +
                ", answerOptions=" + answerOptions +
                ", survey=" + survey +
                '}';
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "answerOption", joinColumns = @JoinColumn(name = "questionID"))
    @MapKeyColumn(name = "answerText")
    @Column(name = "interestId")
    private Map<String, Integer> answerOptions;

    @ManyToOne
    private Survey survey;
    public Question() {
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question(Long questionID, String text, Map<String, Integer> answerOptions) {
        this.questionID = questionID;
        this.text = text;
        this.answerOptions = answerOptions;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Integer> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Map<String, Integer> answerOptions) {
        this.answerOptions = answerOptions;
    }
}