package com.example.restservice.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestID;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "interest_id" ,referencedColumnName = "interestID"),
            inverseJoinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "userID")
    )
    private List<User> users;

    public Interest() {

    }

    public Interest(Long interestID, String name, String description) {
        this.interestID = interestID;
        this.name = name;
        this.description = description;
    }

    public Long getInterestID() {
        return interestID;
    }

    public void setInterestID(Long interestID) {
        this.interestID = interestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<User> getUsers() {
        return users;
    }
}
