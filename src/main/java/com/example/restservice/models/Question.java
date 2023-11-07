package com.example.restservice.models;
import jakarta.persistence.*;
import java.io.Serializable;


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

    @ElementCollection
    @CollectionTable(name="answerOption", joinColumns = @JoinColumn(name = "questionID"))
    @Column(name = "answer")
    private Set<String> answerOptions;

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

    public Question(Long questionID, String text, Set<String> answerOptions) {
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

    public Set<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<String> answerOptions) {
        this.answerOptions = answerOptions;
    }
}