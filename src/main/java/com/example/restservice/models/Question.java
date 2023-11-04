package com.example.restservice.models;
import jakarta.persistence.*;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Set;

@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;
    private String text;

    @ElementCollection
    @CollectionTable(name="answerOption", joinColumns = @JoinColumn(name = "questionID"))
    @Column(name = "answer")
    private Set<String> answerOptions;
    public Question() {
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