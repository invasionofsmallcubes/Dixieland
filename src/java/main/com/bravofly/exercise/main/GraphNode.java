package com.bravofly.exercise.main;


import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private Airports source;
    private List<Edge> edges;

    public GraphNode(Airports source) {
        this.source = source;
        edges = new ArrayList<>();
    }

    public Airports getSource() {
        return source;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode graphNode = (GraphNode) o;

        if (source != graphNode.source) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return source != null ? source.hashCode() : 0;
    }
}
