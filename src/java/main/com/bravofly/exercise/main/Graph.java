package com.bravofly.exercise.main;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Airports> vertexes;
    private final List<Edge> edges;

    public Graph(List<Airports> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getNeighboursOf(Airports vertex) {
        List<Edge> list = new ArrayList<>();
        for(Edge e: edges) {
            if(e.getSource().equals(vertex)) list.add(e);
        }
        return list;
    }

  public List<Edge> getInEdges(Airports vertex) {
    List<Edge> list = new ArrayList<>();
    for(Edge e: edges) {
      if(e.getDestination().equals(vertex)) list.add(e);
    }
    return list;
  }

}