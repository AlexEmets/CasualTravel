package com.casualTravel.restservice.service;

import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.repository.PlaceRepository;
import com.casualTravel.restservice.repository.UserPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserPlaceRepository userPlaceRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Long placeId) {
        return placeRepository.findById(placeId);
    }

    public Optional<Place> getPlaceByGoogleId(Long placeGoogleId) {
        return placeRepository.findByGoogleID(placeGoogleId);
    }

    public List<Place> getPlacesByInterest(Long interestId) {
        return placeRepository.findByInterestsInterestID(interestId);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public void deletePlace(Long placeId) {
        placeRepository.deleteById(placeId);
    }

    public void addUserToPlace(User user, Place place, LocalDateTime timeSpent) {
        // Створюємо новий об'єкт UserPlace з вказаним користувачем, місцем та часом
        UserPlace userPlace = new UserPlace(user, place, timeSpent);

        // Зберігаємо зв'язок у репозиторії
        userPlaceRepository.save(userPlace);
    }

    public void removeUserFromPlace(User user, Place place) {
        // Знаходимо UserPlace за вказаним користувачем та місцем
        Optional<UserPlace> userPlaceOptional = userPlaceRepository.findByUserAndPlace(user, place);

        // Видаляємо UserPlace, якщо знайдено
        userPlaceOptional.ifPresent(userPlaceRepository::delete);
    }

}

