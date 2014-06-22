package com.bravofly.dixieland.logic;

import com.bravofly.dixieland.Edge;
import com.bravofly.dixieland.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm<A> {

    private final Graph<A> graph;
    private List<Edge<A>> edges;
    private Set<A> settledNodes;
    private Set<A> unSettledNodes;
    private Map<A, A> predecessors;
    private Map<A, Integer> distance;

    public DijkstraAlgorithm(Graph<A> graph) {
        this.graph = graph;
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public List<List<A>> getShortestPathForSameArrival(A source) {
        execute(source);
        List<Edge<A>> inEdges = graph.getInEdges(source);
        List<List<A>> paths = new ArrayList<>(inEdges.size());
        for (Edge<A> e : inEdges) {
            LinkedList<A> path = getMinimumPath(e.getSource());
            if (path != null) {
                paths.add(path);
                paths.get(paths.size() - 1).add(source);
            }
        }
        return paths;
    }

    public List<A> getShortestPathForDifferentArrival(A source, A target) {
        execute(source);
        return getMinimumPath(target);
    }

    private void execute(A source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            A node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }

        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            A node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(A node) {
        List<A> adjacentNodes = getNeighbors(node);
        for (A target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(A node, A target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        return 0;
    }

    private List<A> getNeighbors(A node) {
        List<A> neighbors = new ArrayList<>();
        for (Edge<A> edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private A getMinimum(Set<A> airportsList) {
      A minimum = null;
        for (A a : airportsList) {
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

    private boolean isSettled(A node) {
        return settledNodes.contains(node);
    }

    private int getShortestDistance(A destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private LinkedList<A> getMinimumPath(A target) {
        LinkedList<A> path = new LinkedList<>();
      A step = target;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}
