package com.casualTravel.restservice.utils;

import com.casualTravel.restservice.dto.AutoRouteAnswerIn;

import java.util.*;


public class RouteGenerator {

    private double userNatureCoef;
    private double userFunCoef;
    private double userHistoryCoef;
    private double userArtCoef;

    private boolean isMustVisitPlacesNeeded;

    private boolean isOutdoorPlacesNeeded;
    private int placesAmountNeeded;
    private Float priceLevelNeeded;
    public RouteGenerator(List<Point> places, double userNatureCoef, double userFunCoef, double userHistoryCoef, double userArtCoef, AutoRouteAnswerIn surveyAnswers) {
        this.allPlaces = new LinkedList<>();

        this.userNatureCoef = userNatureCoef;
        this.userFunCoef = userFunCoef;
        this.userHistoryCoef = userHistoryCoef;
        this.userArtCoef = userArtCoef;

        this.placesAmountNeeded = surveyAnswers.getLocationsCount();
        this.isMustVisitPlacesNeeded = surveyAnswers.getMustVisitWeight();
        this.isOutdoorPlacesNeeded = surveyAnswers.getOutdoorAction();
        this.priceLevelNeeded = surveyAnswers.getPriceLevel();

        Point startPoint = new Point("Стартова точка", Double.parseDouble(surveyAnswers.getStartLocation().getX()),Double.parseDouble(surveyAnswers.getStartLocation().getY()), 0, 0, 0, 0,
                surveyAnswers.getMustVisitWeight(), surveyAnswers.getOutdoorAction(), surveyAnswers.getPriceLevel());
        places.add(0, startPoint);

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
    private List<Point> allPlaces;

    public void addPlace(Point nodeA) {
        allPlaces.add(nodeA);
    }

    public static RouteGenerator calculateShortestPathFromSource(RouteGenerator graph, Point source, Integer amountOfPlaces) {
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
    public int calculateInterestValue(Point place)
    {
        if(place.isOutdoor() && !isOutdoorPlacesNeeded) return (int) INF; // TODO add logic
        return (int) (1000*(userArtCoef * place.getArtCoef() + userNatureCoef * place.getNatureCoef() + userFunCoef * place.getFunCoef() + userHistoryCoef * place.getHistoryCoef()));
    }
    public void fillGraphWithPlaces(List<Point> places) {
        for (Point p : places) {
            p.setInterestValue(calculateInterestValue(p));
        }

        for (Point place1 : places) {
            for (Point place2 : places) {
                if (!place1.equals(place2)) {
                    double distance = calculateEdgeWeight(place1, place2);
                    System.out.println(place1.getName() + " --- " + place2.getName() + " " + distance);
                    place1.addDestination(place2, distance);
                }
            }
            addPlace(place1); // add every place from function parameter to our graph
        }
    }

    private double calculateEdgeWeight(Point place1, Point place2) {
        int destinationPlaceInterestValue = place2.getInterestValue();
        double distanceBetweenPlaces = findDistance(place1, place2);

        return distanceBetweenPlaces /*/ destinationPlaceInterestValue*/;
    }

    /**
     * Calculate distance between two points in latitude and longitude
     * @returns Distance in Meters
     */
    public static double findDistance(Point place1, Point place2) {

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

    public List<Point> getNodes() {
        return allPlaces;
    }

//    public List<Point> generateRoute(Point placeStart, Point placeFinish, Integer amountOfPlaces) {
//        var graph = RouteGenerator.calculateShortestPathFromSource(this, placeStart, amountOfPlaces);
//
//        LinkedList<Point> route = new LinkedList<>(placeFinish.getShortestPath());
//        route.add(placeFinish); // Додаємо placeFinish в кінець списку
//
//        return route;
//    }

    public static void printRoute(List<Point> places) {
        for (int i = 0; i < places.size(); i++) {
            System.out.print(places.get(i).getName());
            if (i < places.size() - 1) {
                System.out.print(" ---> ");
            }
        }
    }

    // ----------------------------------------------------------------------

    public RouteGenerator(List<Point> allPlaces) {
        this.allPlaces = allPlaces;
    }
    public List<Point> generateRoute() {
        if (allPlaces.isEmpty() || placesAmountNeeded < 1) {
            return new ArrayList<>();
        }

        // Перша вершина - це завжди перший елемент списку
        Point firstPlace = allPlaces.get(0);

        // Створення списку без першої вершини
        List<Point> remainingPlaces = new ArrayList<>(allPlaces);
        remainingPlaces.remove(0);

        // Генерація комбінацій k-1 вершин з remainingPlaces
        Set<Set<Point>> allCombinations = generateCombinations(new LinkedList<>(remainingPlaces), placesAmountNeeded - 1);

        List<Point> shortestPath = new ArrayList<>();
        double shortestDistance = Double.MAX_VALUE;

        for (Set<Point> combination : allCombinations) {
            List<Point> currentPath = new ArrayList<>();
            currentPath.add(firstPlace);
            currentPath.addAll(combination);

            double currentDistance = calculatePathDistance(currentPath);

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                shortestPath = currentPath;
            }
        }

        return shortestPath;
    }
    public static Set<Set<Point>> generateCombinations(List<Point> places, int k) {
        Set<Set<Point>> allCombinations = new HashSet<>();
        generateCombinationsRecursive(places, k, new LinkedList<>(), allCombinations, new ArrayList<>(places));
        return allCombinations;
    }

    private static void generateCombinationsRecursive(List<Point> places, int k, List<Point> current, Set<Set<Point>> allCombinations, List<Point> remaining) {
        if (current.size() == k) {
            allCombinations.add(new HashSet<>(current));
            return;
        }

        for (int i = 0; i < remaining.size(); i++) {
            Point next = remaining.get(i);
            current.add(next);

            generateCombinationsRecursive(places, k, current, allCombinations, remaining.subList(i + 1, remaining.size()));

            current.remove(next);
        }
    }

    public double calculatePathDistance(List<Point> path) {
        if (path.size() < 2) {
            return 0.0; // Немає відстані для шляху з менше ніж двома місцями
        }

        double totalDistance = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            Point currentPlace = path.get(i);
            Point nextPlace = path.get(i + 1);

            Double distance = currentPlace.getAdjacentNodes().get(nextPlace);
            if (distance != null) {
                totalDistance += distance;
            } else {
                // Якщо не існує прямого з'єднання між currentPlace та nextPlace,
                // можна або встановити дуже велике число, або обробити це як помилку
                totalDistance += Double.MAX_VALUE;
            }
        }

        return totalDistance;
    }

    private double INF = 1e9;
}