package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.AutoRouteAnswerIn;
import com.casualTravel.restservice.dto.InterestDTO;
import com.casualTravel.restservice.dto.LocationDTO;
import com.casualTravel.restservice.dto.PlaceOut;
import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.service.JwtService;
import com.casualTravel.restservice.service.PlaceService;
import com.casualTravel.restservice.service.UserService;
import com.casualTravel.restservice.dto.*;
import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<PlaceOut>> getPlaces() {
        List<Place> places = placeService.getAllPlaces();
        List<PlaceOut> responseDTOs = places.stream()
                .map(this::mapToPlaceResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @PostMapping("/autoRoute")
    public ResponseEntity<List<PlaceOut>> getAutoRoutePlaces(@RequestBody AutoRouteAnswerIn autoRouteAnswerIn, @RequestHeader("Authorization") String authorizationHeader) {
       User user = new User();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer ".length()
            String userEmail = jwtService.extractEmail(token);
            user = (User) userService.loadUserByUsername(userEmail);
        } else user = null;

        // Викликайте відповідний метод сервісу для обробки autoRouteAnswerIn та отримання списку Point
        List<Place> places = placeService.autoGenerateRoute(autoRouteAnswerIn, user);

        // Мапте список Point в список PlaceOut
        List<PlaceOut> responseDTOs = places.stream()
                .map(this::mapToPlaceResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    private PlaceOut mapToPlaceResponseDTO(Place place) {
        LocationDTO location = new LocationDTO(place.getPositionX(), place.getPositionY());
        List<InterestDTO> interestDTOs = placeService.getInterestsDTO(place.getInterests());

        return new PlaceOut(
                place.getPlaceID(),
                place.getGoogleID(),
                place.getPlaceName(),
                location,
                place.getVisitTime(),
                place.getVisitCost(),
                interestDTOs
        );
    }

}
