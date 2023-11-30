package com.casualTravel.restservice.service;

import com.casualTravel.restservice.dto.AutoRouteAnswerIn;
import com.casualTravel.restservice.dto.InterestDTO;
import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.repository.PlaceRepository;
import com.casualTravel.restservice.repository.UserPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    public List<InterestDTO> getInterestsDTO(Map<Interest, Double> interests) {
//        return interests.entrySet().stream()
//                .map(entry -> mapToInterestDTO(entry.getKey()))
//                .collect(Collectors.toList());
//    }
//
//    private InterestDTO mapToInterestDTO(Interest interest) {
//        return new InterestDTO(
//                interest.getInterestID(),
//                interest.getName(),
//                interest.getImageURL()
//        );
//    }

    public List<InterestDTO> getInterestsDTO(Map<Interest, Double> interests) {
        List<InterestDTO> interestDTOList = new ArrayList<>();

        for (Map.Entry<Interest, Double> entry : interests.entrySet()) {
            Interest interest = entry.getKey();
            InterestDTO interestDTO = mapToInterestDTO(interest);
            interestDTO.setWage(entry.getValue());
            interestDTOList.add(interestDTO);
        }
        return interestDTOList;
    }

    private InterestDTO mapToInterestDTO(Interest interest) {
        return new InterestDTO(
                interest.getInterestID(),
                interest.getName(),
                interest.getImageURL()
        );
    }

    public List<Place> autoGenerateRoute(AutoRouteAnswerIn autoRouteAnswerIn, User user)
    {
//
//        var placesFromBD = getAllPlaces();
//        for(Place place : placesFromBD)
//        {
//            Point routePlace = new Point(place.getPlaceName(), Double.parseDouble(place.getPositionX()), Double.parseDouble(place.getPositionY()),
//                    place.getInterests().get());
//        }
//        RouteGenerator routeGenerator();
        return new ArrayList<>();
    }
}

