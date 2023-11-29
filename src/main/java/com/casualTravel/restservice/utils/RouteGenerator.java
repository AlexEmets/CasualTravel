package com.casualTravel.restservice.utils;

import java.util.*;

public class RouteGenerator {

    private double userNatureCoef;
    private double userFunCoef;
    private double userHistoryCoef;
    private double userArtCoef;


    public RouteGenerator(Set<Place> places, double userNatureCoef, double userFunCoef, double userHistoryCoef, double userArtCoef) {
        this.userNatureCoef = userNatureCoef;
        this.userFunCoef = userFunCoef;
        this.userHistoryCoef = userHistoryCoef;
        this.userArtCoef = userArtCoef;
        fillGraphWithPlaces(places);
    }

    public double getNatureCoef() {
        return userNatureCoef;
    }

    public double getFunCoef() {
        return userFunCoef;
    }

    public double getHistoryCoef() {
        return userHistoryCoef;
    }

    public double getArtCoef() {
        return userArtCoef;
    }


    public void setNatureCoef(double natureCoef) {
        this.userNatureCoef = natureCoef;
    }

    public void setFunCoef(double funCoef) {
        this.userFunCoef = funCoef;
    }

    public void setHistoryCoef(double historyCoef) {
        this.userHistoryCoef = historyCoef;
    }
    public void setArtCoef(double artCoef) {
        this.userArtCoef = artCoef;
    }
    private final Set<Place> allPlaces = new HashSet<>();

    public void addPlace(Place nodeA) {
        allPlaces.add(nodeA);
    }

    public static RouteGenerator calculateShortestPathFromSource(RouteGenerator graph, Place source) {
        source.setDistance(0.0);

        Set<Place> settledNodes = new HashSet<>();
        Set<Place> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {

            Place currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<Place, Double> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Place adjacentNode = adjacencyPair.getKey();
                Double edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Place getLowestDistanceNode(Set <Place> unsettledNodes) {
        Place lowestDistanceNode = null;
        double lowestDistance = 1e18;

        for (Place node: unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }


    private static void calculateMinimumDistance(Place evaluationNode,
                                                 Double edgeWeigh, Place sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Place> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    // TODO
    private int calculateInterestValue(Place place)
    {
        return (int) (1000*(userArtCoef * place.getArtCoef() + userNatureCoef * place.getNatureCoef() + userFunCoef * place.getFunCoef() + userHistoryCoef * place.getHistoryCoef()));
    }
    public void fillGraphWithPlaces(Set<Place> places) {
        for (Place p : places) {
            p.setInterestValue(calculateInterestValue(p));
        }

        for (Place place1 : places) {
            for (Place place2 : places) {
                if (!place1.equals(place2)) {
                    double distance = calculateEdgeWeight(place1, place2);
                    place1.addDestination(place2, distance);
                }
            }
            addPlace(place1); // add every place from function parameter to our graph
        }
    }

    private double calculateEdgeWeight(Place place1, Place place2) {
        int destinationPlaceInterestValue = place2.getInterestValue();
        double distanceBetweenPlaces = findDistance(place1, place2);

        return distanceBetweenPlaces / destinationPlaceInterestValue;
    }

    /**
     * Calculate distance between two points in latitude and longitude
     * @returns Distance in Meters
     */
    public static double findDistance(Place place1, Place place2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(place2.getLatitude() - place1.getLatitude());
        double lonDistance = Math.toRadians(place2.getLongitude() - place1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(place1.getLatitude())) * Math.cos(Math.toRadians(place2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public Set<Place> getNodes() {
        return allPlaces;
    }

    public List<Place> generateRoute(Place placeStart, Place placeFinish) {
        var graph = RouteGenerator.calculateShortestPathFromSource(this, placeStart);

        LinkedList<Place> route = new LinkedList<>(placeFinish.getShortestPath());
        route.add(placeFinish); // Додаємо placeFinish в кінець списку

        return route;
    }

    public static void printRoute(List<Place> places) {
        for (int i = 0; i < places.size(); i++) {
            System.out.print(places.get(i).getName());
            if (i < places.size() - 1) {
                System.out.print(" ---> ");
            }
        }
    }
}