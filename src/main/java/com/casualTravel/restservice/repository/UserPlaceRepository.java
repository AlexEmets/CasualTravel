package com.casualTravel.restservice.repository;

import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.models.User;
import com.casualTravel.restservice.models.UserPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPlaceRepository extends JpaRepository<UserPlace, Long> {
    Optional<UserPlace> findByUserAndPlace(User user, Place place);
}
