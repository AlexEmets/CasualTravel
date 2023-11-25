package com.casualTravel.restservice.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Answer")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerID;

    private String answerText;

    @ManyToOne
    @JoinColumn(name = "questionID", nullable = false)
    private Question question;

//    @ElementCollection
//    @CollectionTable(name = "answer_options", joinColumns = @JoinColumn(name = "answerID"))
//    @Column(name = "option")
//    private List<String> answerOptions;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "answer_interest",
            joinColumns = @JoinColumn(name = "answerId", referencedColumnName = "answerID"),
            inverseJoinColumns = @JoinColumn(name = "interestId", referencedColumnName = "interestID")
    )
    private List<Interest> interests;

    public Answer() {
    }

    public Long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Long answerID) {
        this.answerID = answerID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}
