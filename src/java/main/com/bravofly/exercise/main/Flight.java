package com.bravofly.exercise.main;

public class Flight {
    private Airports source;
    private final Airports destination;
    private final int travelTime;

    public Flight(Airports source, Airports destination, int travelTime) {
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

        Flight flight = (Flight) o;

        if (destination != flight.destination) return false;
        if (source != flight.source) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }
}
