package com.bravofly.exercise.main;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TravelTimeTest {

    private Routes routes;
    private List<Airports> itinerary = new ArrayList<>();

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

    @Test
    public void scenarioOne() {
        givenTheItinerary(Airports.Magenta, Airports.Navy, Airports.Orange);
        thenICanAssertTravelTimeOf(9);
    }

    @Test
    public void scenarioTwo() {
        givenTheItinerary(Airports.Magenta, Airports.Pink);
        thenICanAssertTravelTimeOf(5);
    }

    @Test
    public void scenarioThree() {
        givenTheItinerary(Airports.Magenta, Airports.Pink, Airports.Orange);
        thenICanAssertTravelTimeOf(13);
    }

    @Test
    public void scenarioFour() {
        givenTheItinerary(Airports.Magenta, Airports.Quartz, Airports.Navy, Airports.Orange, Airports.Pink);
        thenICanAssertTravelTimeOf(22);
    }

    @Test
    public void scenarioFive() {
        givenTheItinerary(Airports.Magenta, Airports.Quartz, Airports.Pink);
        thenICanAssertTravelTimeOf(0);
    }

    private void givenTheItinerary(Airports... airports) {
        for (Airports a : airports) itinerary.add(a);
    }

    private void thenICanAssertTravelTimeOf(int expectedTravelTime) {
        assertThat(routes.getTravelTime(itinerary), is(expectedTravelTime));
    }

}
