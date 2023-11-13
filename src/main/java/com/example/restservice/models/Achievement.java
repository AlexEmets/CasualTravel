package com.example.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Achievement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievementId;

    private String name;
    private String level;
    private String achievementPhotoUrl;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_achievementID",
            joinColumns = @JoinColumn(name = "achievementId" ,referencedColumnName = "achievementID"),
            inverseJoinColumns = @JoinColumn(name = "userId" , referencedColumnName = "userID")
    )
    @JsonIgnore
    private List<User> users;
}
