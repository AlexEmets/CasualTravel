package com.casualTravel.restservice;

import com.casualTravel.restservice.controller.InterestController;
import com.casualTravel.restservice.dto.AutoRouteAnswerIn;
import com.casualTravel.restservice.dto.LocationDTO;
import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.service.InterestService;
import com.casualTravel.restservice.service.PlaceService;
import com.casualTravel.restservice.utils.Point;
import com.casualTravel.restservice.utils.RouteGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteGeneratorTests {
    private List<Point> getSomePoints(Integer amount) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < amount; i++) { // Генеруємо 10 точок
            double natureCoef = 0.3;
            double funCoef = 0.4;
            double historyCoef = 0.5;
            double artCoef = 0.8;

            boolean mustVisit = i % 2 == 0; // Чергуємо значення
            boolean outdoorAction = i % 3 == 0; // Чергуємо значення

            Point routePlace = new Point(
                    "Place " + i, // Назва місця
                    30.0 + i, // Широта, змінюється з кожною ітерацією
                    50.0 + i, // Довгота, змінюється з кожною ітерацією
                    natureCoef, funCoef, historyCoef, artCoef,
                    mustVisit, outdoorAction, 50.0F + i // Змінюємо вартість
            );
            points.add(routePlace);
        }
        return points;
    }

    private AutoRouteAnswerIn createSurveyAnswers(int locationsCount, float priceLevel, boolean mustVisitWeight, String restPreffered, boolean outdoorAction, LocationDTO startLocation, LocationDTO endLocation) {
        AutoRouteAnswerIn surveyAnswers = new AutoRouteAnswerIn();
        surveyAnswers.setLocationsCount(locationsCount);
        surveyAnswers.setPriceLevel(priceLevel);
        surveyAnswers.setMustVisitWeight(mustVisitWeight);
        surveyAnswers.setRestPreffered(restPreffered);
        surveyAnswers.setOutdoorAction(outdoorAction);
        surveyAnswers.setStartLocation(startLocation);
        surveyAnswers.setEndLocation(endLocation);
        return surveyAnswers;
    }
    @Test
    public void testEmptyListOfPlaces() {
        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(0, 1.0f, false, "sports", false,
                new LocationDTO("5", "6"), new LocationDTO("7", "8"));

        RouteGenerator generator = new RouteGenerator(new ArrayList<>(), 0.5, 0.5, 0.5, 0.5, surveyAnswers);
        assertTrue(generator.generateRoute().isEmpty());
    }

    @Test
    public void testSinglePlace() {
        List<Point> singlePointList = new ArrayList<>(Arrays.asList(new Point("Point1", 30.545500, 50.45660,
                0.5, 0.5, 0.5, 0.5,
                true, false, 50.0F)));

        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(1, 1.0f, false, "sports", false,
                new LocationDTO("5", "6"), new LocationDTO("7", "8"));

        RouteGenerator generator = new RouteGenerator(singlePointList,
                0.5, 0.5, 0.5, 0.5,
                surveyAnswers);
        assertEquals(1, generator.generateRoute().size());
    }

    @Test
    public void testZeroPlacesNeeded() {
        List<Point> placesList = new ArrayList<>(Arrays.asList(new Point("Point1", 30.545500, 50.45660,
                0.5, 0.5, 0.5, 0.5,
                true, false, 50.0F)));

        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(0, 1.0f, false, "sports", false,
                new LocationDTO("5", "6"), new LocationDTO("7", "8"));

        RouteGenerator generator = new RouteGenerator(placesList,
                0.5, 0.5, 0.5, 0.5,
                surveyAnswers);
        assertTrue(generator.generateRoute().isEmpty());
    }
    @Test
    public void testValidNumberOfPlaces() {
        List<Point> placesList = getSomePoints(5);

        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(5, 1.0f, false, "sports", false,
                new LocationDTO("5", "6"), new LocationDTO("7", "8"));

        RouteGenerator generator = new RouteGenerator(placesList,
                0.5, 0.5, 0.5, 0.5,
                surveyAnswers);
        assertEquals(5, generator.generateRoute().size());
    }
    @Test
    public void testInterestCoefficients() {
        List<Point> singlePointList = new ArrayList<>(Arrays.asList(new Point("Point1",
                30.545500, 50.45660, 0.5, 0.5, 0.5, 0.5,
                true, false, 50.0F)));

        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(1, 1.0f, false, "sports", true,
                new LocationDTO("5", "6"), new LocationDTO("7", "2"));

        RouteGenerator generator = new RouteGenerator(singlePointList, 0.5, 0.8, 0.5, 0.5, surveyAnswers);
        int expectedInterestValue = 1150;
        assertEquals(expectedInterestValue, generator.calculateInterestValue(singlePointList.get(1)));
    }
    @Test
    public void testDistanceBetweenPoints() {
        Point pointA = new Point("Point1", 30.545500, 50.45660,
                0.5,0.5, 0.5, 0.5,
                true, false, 50.0F);
        Point pointB = new Point("Point2", 30.545554, 50.45667,
                0.1,0.4, 0.7, 0.3,
                false, false, 0F);
        double expectedDistance = 8.67; // Розрахунок очікуваної відстані
        assertEquals(expectedDistance, RouteGenerator.findDistance(pointA, pointB), 0.01);
    }

    @Test
    public void testPriceLevelFiltering() {
        List<Point> placesList = getSomePoints(5);

        AutoRouteAnswerIn surveyAnswers = createSurveyAnswers(1, 1.0f, false, "sports", true,
                new LocationDTO("5", "6"), new LocationDTO("7", "2"));

        RouteGenerator generator = new RouteGenerator(placesList, 0.5, 0.8, 0.5, 0.5, surveyAnswers);

        Float expectedPriceLevel = 1F;
        for (Point place : generator.generateRoute()) {
            assertTrue(place.getPriceLevel() <= expectedPriceLevel);
        }
    }
}
