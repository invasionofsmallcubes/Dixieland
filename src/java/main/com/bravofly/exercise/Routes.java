package com.bravofly.exercise;

import com.bravofly.exercise.logic.DepthFirstSearch;
import com.bravofly.exercise.logic.DijkstraAlgorithm;
import com.bravofly.exercise.visitor.hops.ExactHopsVisitor;
import com.bravofly.exercise.visitor.time.ExactTimeVisitor;
import com.bravofly.exercise.visitor.hops.MaxHopsVisitor;

import java.util.List;

public class Routes extends Graph<Airports> {
    private DepthFirstSearch<Airports> dfs = new DepthFirstSearch<>();

    public int getTravelTime(List<Airports> itinerary) {
        int sum = 0;
        for (int i = 0; i < itinerary.size() - 1; i++) {
            Edge<Airports> edgeToSearch = new Edge<>(itinerary.get(i), itinerary.get(i + 1));
            if (!edges.contains(edgeToSearch)) {
                return -1;
            }
            sum += recoverTravelTime(edgeToSearch);
        }
        return sum;
    }

    public int getRoutesWithExactTravelTime(int timeInterval, Airports source, Airports target) {
        return dfs.apply(this, 0, source, new ExactTimeVisitor(timeInterval, target, 0));
    }

    public int getRoutesWithAtMostHops(int maxHops, Airports source, Airports destination) {
        return dfs.apply(this, 0, source, new MaxHopsVisitor(maxHops, destination, 0));
    }

    public int getRoutesWithExactHops(int maxHops, Airports source, Airports destination) {
        return dfs.apply(this, 0, source, new ExactHopsVisitor(maxHops, destination, 0));
    }

    public int getFastestTravelTime(Airports departure, Airports arrival) {
        DijkstraAlgorithm<Airports> d = new DijkstraAlgorithm<>(this);
        if (departure.equals(arrival)) return getMinPathTravelTime(new DijkstraAlgorithm<>(this).getShortestPathForSameNode(departure, arrival));
        return getTravelTime(d.getShortestPathForDifferentNodes(departure, arrival));
    }

    private int recoverTravelTime(Edge<Airports> toSearch) {
        for (Edge<Airports> f : edges) {
            if (f.equals(toSearch)) {
                return f.getWeight();
            }
        }
        return 0;
    }

    private int getMinPathTravelTime(List<List<Airports>> paths) {
        int min = Integer.MAX_VALUE;
        for (List<Airports> path : paths) {
            int t = getTravelTime(path);
            if (t < min) {
                min = t;
            }
        }
        return min;
    }


}