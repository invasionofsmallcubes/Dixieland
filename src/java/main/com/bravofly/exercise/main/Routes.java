package com.bravofly.exercise.main;

import java.util.Set;

public class Routes {

    private Set<Flight> flights;

    public Routes(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Flight> getFlights() {
        return flights;
    }
}
