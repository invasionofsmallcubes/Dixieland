package com.bravofly.exercise.main;

import java.util.LinkedList;
import java.util.List;

public class Routes {
    private Graph g;

    public Routes(Graph g) {
        this.g = g;
    }

    public int getTravelTime(List<Airports> itinerary) {
        int sum = 0;
        for (int i = 0; i < itinerary.size() - 1; i++) {
            Edge edgeToSearch = new Edge(itinerary.get(i), itinerary.get(i + 1));
            if (!g.getEdges().contains(edgeToSearch)) {
                return 0;
            }
            sum += recoverTravelTime(edgeToSearch);
        }
        return sum;
    }

    private int recoverTravelTime(Edge toSearch) {
        for (Edge f : g.getEdges()) {
            if (f.equals(toSearch)) {
                return f.getWeight();
            }
        }
        return 0;
    }

    private int paths(int maxVertex, Airports s, Airports e, boolean goDeep) {
        return depthFirstSearch(0, maxVertex, s, e, goDeep);
    }

    private int depthFirstSearch(int currentDepth, int maxDepth, Airports s, Airports e, boolean goDeep) {
        int counter = 0;
        if (currentDepth > maxDepth) return counter;

        int newDepth = currentDepth + 1;
        for (Edge x : g.getNeighboursOf(s)) {
            counter += ((x.getDestination().equals(e) && (goDeep || (currentDepth == maxDepth)))) ? 1 : 0;
            counter += depthFirstSearch(newDepth, maxDepth, x.getDestination(), e, goDeep);
        }
        return counter;
    }

    public int getPathsWithEqualOrLessThenHops(int maxVertex, Airports source, Airports destination) {
        return paths(maxVertex, source, destination, true);
    }

    public int getPathsWithEqualsHops(int maxVertex, Airports source, Airports destination) {
        return paths(maxVertex, source, destination, false);
    }

    public int getPathsWithExactTimeInterval(int depth, Airports source, Airports target) {
        return pathsB(0, depth, source, target);
    }

    private int pathsB(int currentDepth, int depth, Airports source, Airports target) {
        int counter = 0;
        if (currentDepth > depth) {
            return counter;
        }
        for(Edge x: g.getNeighboursOf(source)) {
            if(x.getDestination().equals(target) && currentDepth + x.getWeight() < depth) {
                counter++;
            }
            counter += pathsB(currentDepth+x.getWeight(), depth, x.getDestination(), target);
        }
        return counter;
    }

//    public void getAllPathsUnderAnInterval(int depth, Airports source, Airports target){
//
//        List<List<Airports>> result = new LinkedList<>();
//
//        pathsC(new LinkedList<Airports>(), result, 0, depth, source, target);
//
//        for (List<Airports> list : result) {
//            System.out.println(list + " -> " + getTravelTime(list));
//        }
//
//    }
//
//    private void pathsC(List<Airports> currentAirports, List<List<Airports>> foundPaths, int currentDepth,
//                        int depth, Airports source, Airports target) {
//
//        if (currentDepth > depth) {
//            return;
//        }
//
//        currentAirports.add(source);
//
//        List<Airports> newCurrentAirports;
//        for(Edge x: g.getNeighboursOf(source)) {
//            if(x.getDestination().equals(target) && currentDepth + x.getWeight() < depth) {
//                currentAirports.add(x.getDestination());
//                foundPaths.add(currentAirports);
//
//                newCurrentAirports = new LinkedList<>();
//                for(int i=0; i < currentAirports.size()-1; i++) {
//                    newCurrentAirports.add(currentAirports.get(i));
//                }
//            } else {
//                newCurrentAirports = new LinkedList<>();
//                for (int i = 0; i < currentAirports.size(); i++) {
//                    newCurrentAirports.add(currentAirports.get(i));
//                }
//            }
//
//            pathsC(newCurrentAirports, foundPaths, currentDepth+x.getWeight(), depth, x.getDestination(), target);
//        }
//    }
}