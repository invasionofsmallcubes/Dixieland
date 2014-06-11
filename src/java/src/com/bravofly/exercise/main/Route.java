package com.bravofly.exercise.main;

public class Route {
    private Airports source;
    private final Airports destination;
    private final int travelTime;

    public Route(Airports source, Airports destination, int travelTime) {
        this.source = source;
        this.destination = destination;
        this.travelTime = travelTime;
    }

    public Airports getSource() {
        return source;
    }

    public Airports getDestination() {
        return destination;
    }

    public int getTravelTime() {
        return travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (travelTime != route.travelTime) return false;
        if (destination != route.destination) return false;
        if (source != route.source) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + travelTime;
        return result;
    }
}
