package com.casualTravel.restservice.utils;

import java.util.*;

public class RouteGenerator {

    private double userNatureCoef;
    private double userFunCoef;
    private double userHistoryCoef;
    private double userArtCoef;


    public RouteGenerator(Set<Point> points, double userNatureCoef, double userFunCoef, double userHistoryCoef, double userArtCoef) {
        this.userNatureCoef = userNatureCoef;
        this.userFunCoef = userFunCoef;
        this.userHistoryCoef = userHistoryCoef;
        this.userArtCoef = userArtCoef;
        fillGraphWithPlaces(points);
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
    private final Set<Point> allPoints = new HashSet<>();

    public void addPlace(Point nodeA) {
        allPoints.add(nodeA);
    }

    public static RouteGenerator calculateShortestPathFromSource(RouteGenerator graph, Point source) {
        source.setDistance(0.0);

        Set<Point> settledNodes = new HashSet<>();
        Set<Point> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {

            Point currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<Point, Double> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Point adjacentNode = adjacencyPair.getKey();
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

    private static Point getLowestDistanceNode(Set <Point> unsettledNodes) {
        Point lowestDistanceNode = null;
        double lowestDistance = 1e18;

        for (Point node: unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }


    private static void calculateMinimumDistance(Point evaluationNode,
                                                 Double edgeWeigh, Point sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Point> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    // TODO
    private int calculateInterestValue(Point point)
    {
        return (int) (1000*(userArtCoef * point.getArtCoef() + userNatureCoef * point.getNatureCoef() + userFunCoef * point.getFunCoef() + userHistoryCoef * point.getHistoryCoef()));
    }
    public void fillGraphWithPlaces(Set<Point> points) {
        for (Point p : points) {
            p.setInterestValue(calculateInterestValue(p));
        }

        for (Point point1 : points) {
            for (Point point2 : points) {
                if (!point1.equals(point2)) {
                    double distance = calculateEdgeWeight(point1, point2);
                    point1.addDestination(point2, distance);
                }
            }
            addPlace(point1); // add every place from function parameter to our graph
        }
    }

    private double calculateEdgeWeight(Point point1, Point point2) {
        int destinationPlaceInterestValue = point2.getInterestValue();
        double distanceBetweenPlaces = findDistance(point1, point2);

        return distanceBetweenPlaces / destinationPlaceInterestValue;
    }

    /**
     * Calculate distance between two points in latitude and longitude
     * @returns Distance in Meters
     */
    public static double findDistance(Point point1, Point point2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(point2.getLatitude() - point1.getLatitude());
        double lonDistance = Math.toRadians(point2.getLongitude() - point1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(point1.getLatitude())) * Math.cos(Math.toRadians(point2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public Set<Point> getNodes() {
        return allPoints;
    }

    public List<Point> generateRoute(Point pointStart, Point pointFinish) {
        var graph = RouteGenerator.calculateShortestPathFromSource(this, pointStart);

        LinkedList<Point> route = new LinkedList<>(pointFinish.getShortestPath());
        route.add(pointFinish); // Додаємо pointFinish в кінець списку

        return route;
    }

    public static void printRoute(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            System.out.print(points.get(i).getName());
            if (i < points.size() - 1) {
                System.out.print(" ---> ");
            }
        }
    }
}