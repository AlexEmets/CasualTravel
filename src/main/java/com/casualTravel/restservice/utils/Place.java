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

    private double natureCoef;
    private double funCoef;
    private double historyCoef;
    private double artCoef;


    public double getNatureCoef() {
        return natureCoef;
    }

    public double getFunCoef() {
        return funCoef;
    }

    public double getHistoryCoef() {
        return historyCoef;
    }

    public double getArtCoef() {
        return artCoef;
    }

    public void setNatureCoef(double natureCoef) {
        this.natureCoef = natureCoef;
    }

    public void setFunCoef(double funCoef) {
        this.funCoef = funCoef;
    }

    public void setHistoryCoef(double historyCoef) {
        this.historyCoef = historyCoef;
    }
    public void setArtCoef(double artCoef) {
        this.artCoef = artCoef;
    }
    Map<Place, Double> adjacentNodes = new HashMap<>();

    public void addDestination(Place destination, double distance) {
        adjacentNodes.put(destination, distance);
    }


    public Place(String name, double longitude, double latitude, double natureCoef, double historyCoef, double funCoef, double artCoef) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.natureCoef = natureCoef;
        this.funCoef = funCoef;
        this.historyCoef = historyCoef;
        this.artCoef = artCoef;
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