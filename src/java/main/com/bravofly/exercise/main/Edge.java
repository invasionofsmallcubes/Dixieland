package com.bravofly.exercise.main;

public class Edge  {
    private String id;
    private Airports source;
    private Airports destination;
    private int weight;

    public Edge(String id, Airports source, Airports destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Airports source, Airports destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }
    public Airports getDestination() {
        return destination;
    }

    public Airports getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (destination != edge.destination) return false;
        if (source != edge.source) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }
}
