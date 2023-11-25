package com.casualTravel.restservice.repository;

import com.casualTravel.restservice.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByInterestsInterestID(Long interestId);
    Optional<Place> findByGoogleID(Long googleId);
}
