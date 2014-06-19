package com.bravofly.exercise;

import java.util.ArrayList;
import java.util.List;

public class Graph<A> {
    private final List<Edge<A>> edges;

    public Graph(List<Edge<A>> edges) {
        this.edges = edges;
    }

    public List<Edge<A>> getEdges() {
        return edges;
    }

    public List<Edge<A>> getNeighboursOf(A vertex) {
        List<Edge<A>> list = new ArrayList<>();
        for(Edge e: edges) {
            if(e.getSource().equals(vertex)) list.add(e);
        }
        return list;
    }

  public List<Edge<A>> getInEdges(A vertex) {
    List<Edge<A>> list = new ArrayList<>();
    for(Edge<A> e: edges) {
      if(e.getDestination().equals(vertex)) list.add(e);
    }
    return list;
  }

}