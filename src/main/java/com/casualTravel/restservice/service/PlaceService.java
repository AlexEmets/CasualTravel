package com.casualTravel.restservice.service;

import com.casualTravel.restservice.dto.AutoRouteAnswerIn;
import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.repository.PlaceRepository;
import com.casualTravel.restservice.repository.UserPlaceRepository;
import com.casualTravel.restservice.utils.Point;
import com.casualTravel.restservice.utils.RouteGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserPlaceRepository userPlaceRepository;
    private final InterestService interestService;

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

    public List<Place> autoGenerateRoute(AutoRouteAnswerIn autoRouteAnswerIn, User user)
    {
        var placesFromBD = getAllPlaces();
        Interest interest1 = interestService.getInterestById(1L).orElse(null);
        Interest interest2 = interestService.getInterestById(2L).orElse(null);
        Interest interest3 = interestService.getInterestById(3L).orElse(null);
        Interest interest4 = interestService.getInterestById(4L).orElse(null);

        List<Point> points = new ArrayList<>();
        for(Place place : placesFromBD)
        {
            Point routePlace = new Point(place.getPlaceName(), Double.parseDouble(place.getPositionX()), Double.parseDouble(place.getPositionY()),
                    place.getInterests().get(interest1), place.getInterests().get(interest2), place.getInterests().get(interest3), place.getInterests().get(interest4), place.getMustVisit(), place.getOutdoorAction(), place.getPreferentialCost());
            points.add(routePlace);
        }
        RouteGenerator routeGenerator = new RouteGenerator(
                points,
                0.5,
                0.5,
                0.5,
                0.5,
                autoRouteAnswerIn
        );
        var route = routeGenerator.generateRoute();

        RouteGenerator.printRoute(route);

        List<Place> placesList = new LinkedList<>();
        for(Point point : route)
        {
            Place place = new Place();
            place.setPlaceName(point.getName());

            place.setPositionX(Double.toString(point.getLongitude()));
            place.setPositionY(Double.toString(point.getLatitude()));
            placesList.add(place);
        }
        return placesList;
    }
}

