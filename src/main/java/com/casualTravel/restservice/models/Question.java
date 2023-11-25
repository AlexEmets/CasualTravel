package com.casualTravel.restservice.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Question implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;

    private String text;

//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "answerOption", joinColumns = @JoinColumn(name = "questionID"))
//    @MapKeyColumn(name = "answerText")
//    @Column(name = "interestId")
//    private Map<String, Integer> answerOptions;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Answer> answerOptions;

    public Question() {
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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
}