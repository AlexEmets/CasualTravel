package com.casualTravel.restservice.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;
    private String text;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Answer> answerOptions;

    // Конструктори, геттери, сеттери і toString метод
    public Question() {
    }

    public Question(Long questionID, String text, List<Answer> answerOptions) {
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

    public List<Answer> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<Answer> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", text='" + text + '\'' +
                ", answerOptions=" + answerOptions +
                ", survey=" + survey +
                '}';
    }
}