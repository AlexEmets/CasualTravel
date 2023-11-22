package com.casualTravel.restservice.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Place {

    private final String name;

    private List<Place> shortestPath = new LinkedList<>();

    private Double distance = 1e18;

    private Integer interestValue;

    public double longitude;
    private double latitude;
    Map<Place, Double> adjacentNodes = new HashMap<>();

    public void addDestination(Place destination, double distance) {
        adjacentNodes.put(destination, distance);
    }

    public Place(String name) {
        this.name = name;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public void setShortestPath(List<Place> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Place> getShortestPath() {
        return shortestPath;
    }

    public Map<Place, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Place, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public void setInterestValue(int interestValue)
    {
        this.interestValue = interestValue;
    }
    public String getName() {
        return name;
    }

    public int getInterestValue() {
        return interestValue;
    }

    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}