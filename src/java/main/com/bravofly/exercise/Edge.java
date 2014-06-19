package com.bravofly.exercise;

public class Edge<T>  {
    private String label;
    private T source;
    private T destination;
    private int weight;

    public Edge(String label, T source, T destination, int weight) {
        this.label = label;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(T source, T destination) {
        this.source = source;
        this.destination = destination;
    }

    public T getDestination() {
        return destination;
    }

    public T getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return label;
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
