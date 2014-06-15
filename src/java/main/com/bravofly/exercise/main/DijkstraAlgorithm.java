package com.bravofly.exercise.main;

import java.util.*;

public class DijkstraAlgorithm {

    private List<Airports> nodes;
    private List<Edge> edges;
    private Set<Airports> settledNodes;
    private Set<Airports> unSettledNodes;
    private Map<Airports, Airports> predecessors;
    private Map<Airports, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        this.nodes = new ArrayList<>(graph.getAirports());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(Airports source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Airports node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }

        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Airports node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Airports node) {
        List<Airports> adjacentNodes = getNeighbors(node);
        for (Airports target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Airports node, Airports target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Airports> getNeighbors(Airports node) {
        List<Airports> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Airports getMinimum(Set<Airports> airportsList) {
        Airports minimum = null;
        for (Airports a : airportsList) {
            if (minimum == null) {
                minimum = a;
            } else {
                if (getShortestDistance(a) < getShortestDistance(minimum)) {
                    minimum = a;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Airports Airports) {
        return settledNodes.contains(Airports);
    }

    private int getShortestDistance(Airports destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Airports> getPath(Airports target) {
        LinkedList<Airports> path = new LinkedList<>();
        Airports step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

} 
