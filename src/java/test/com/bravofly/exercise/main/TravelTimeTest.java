package com.bravofly.exercise.main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TravelTimeTest extends CommonsTest {


    private List<Airports> itinerary = new ArrayList<>();

    @Test
    public void scenarioOne() {
        givenTheItinerary(Airports.M, Airports.N, Airports.O);
        thenICanAssertTravelTimeOf(9);
    }

    @Test
    public void scenarioTwo() {
        givenTheItinerary(Airports.M, Airports.P);
        thenICanAssertTravelTimeOf(5);
    }

    @Test
    public void scenarioThree() {
        givenTheItinerary(Airports.M, Airports.P, Airports.O);
        thenICanAssertTravelTimeOf(13);
    }

    @Test
    public void scenarioFour() {
        givenTheItinerary(Airports.M, Airports.Q, Airports.N, Airports.O, Airports.P);
        thenICanAssertTravelTimeOf(22);
    }

    @Test
    public void scenarioFive() {
        givenTheItinerary(Airports.M, Airports.Q, Airports.P);
        thenICanAssertTravelTimeOf(0);
    }

    private void givenTheItinerary(Airports... airports) {
        for (Airports a : airports) itinerary.add(a);
    }

    private void thenICanAssertTravelTimeOf(int expectedTravelTime) {
        assertThat(routes.getTravelTime(itinerary), is(expectedTravelTime));
    }

}
