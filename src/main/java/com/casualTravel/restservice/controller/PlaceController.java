package com.casualTravel.restservice.controller;

import com.casualTravel.restservice.dto.InterestDTO;
import com.casualTravel.restservice.dto.LocationDTO;
import com.casualTravel.restservice.dto.PlaceOut;
import com.casualTravel.restservice.models.*;
import com.casualTravel.restservice.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceOut>> getPlaces() {
        List<Place> places = placeService.getAllPlaces();
        List<PlaceOut> responseDTOs = places.stream()
                .map(this::mapToPlaceResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    private PlaceOut mapToPlaceResponseDTO(Place place) {
        LocationDTO location = new LocationDTO(place.getPositionX(), place.getGetPositionY());
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
