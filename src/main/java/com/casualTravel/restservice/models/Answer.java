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

    @ElementCollection
    @CollectionTable(name = "answer_options", joinColumns = @JoinColumn(name = "answerID"))
    @Column(name = "option")
    private List<String> answerOptions;

    public Answer() {
    }

    public Answer(Long answerID, List<String> answerOptions) {
        this.answerID = answerID;
        this.answerOptions = answerOptions;
    }

    public Long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Long answerID) {
        this.answerID = answerID;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerID=" + answerID +
                ", answerOptions=" + answerOptions +
                '}';
    }
}