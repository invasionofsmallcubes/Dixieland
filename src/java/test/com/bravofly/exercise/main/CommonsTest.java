package com.bravofly.exercise.main;

import org.junit.Before;

import java.util.HashSet;
import java.util.Set;

public class CommonsTest {

    protected Routes routes;

    @Before
    public void setUp() throws Exception {

        Set<Flight> flights = new HashSet<>();

        flights.add(new Flight(Airports.Magenta, Airports.Navy, 5));
        flights.add(new Flight(Airports.Navy, Airports.Orange, 4));
        flights.add(new Flight(Airports.Orange, Airports.Pink, 8));
        flights.add(new Flight(Airports.Pink, Airports.Orange, 8));
        flights.add(new Flight(Airports.Pink, Airports.Quartz, 6));
        flights.add(new Flight(Airports.Magenta, Airports.Pink, 5));
        flights.add(new Flight(Airports.Orange, Airports.Quartz, 2));
        flights.add(new Flight(Airports.Quartz, Airports.Navy, 3));
        flights.add(new Flight(Airports.Magenta, Airports.Quartz, 7));

        routes = new Routes(flights);
    }
}
