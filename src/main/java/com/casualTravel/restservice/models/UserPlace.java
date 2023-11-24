package com.casualTravel.restservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_place")
public class UserPlace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "placeId", referencedColumnName = "placeID")
  private Place place;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userID")
  private User user;

  @Column(name = "time_visit")
  private LocalDateTime timeField;

  public UserPlace() {

  }

  public UserPlace(User user, Place place, LocalDateTime timeField) {
    this.place = place;
    this.user = user;
    this.timeField = timeField;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getTimeField() {
    return timeField;
  }

  public void setTimeField(LocalDateTime timeField) {
    this.timeField = timeField;
  }


}
