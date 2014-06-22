package com.bravofly.dixieland;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TravelTimeTest extends CommonsTest {

    private List<Airports> itinerary = new ArrayList<>();
    private int travelTime;

    @Test
    public void scenarioOne() {
        givenTheItinerary(Airports.M, Airports.N, Airports.O);
        whenIRecoverTheTravelTime();
        thenIHaveATravelTimeOf(9);
    }

    @Test
    public void scenarioTwo() {
        givenTheItinerary(Airports.M, Airports.P);
        whenIRecoverTheTravelTime();
        thenIHaveATravelTimeOf(5);
    }

    @Test
    public void scenarioThree() {
        givenTheItinerary(Airports.M, Airports.P, Airports.O);
        whenIRecoverTheTravelTime();
        thenIHaveATravelTimeOf(13);
    }

    @Test
    public void scenarioFour() {
        givenTheItinerary(Airports.M, Airports.Q, Airports.N, Airports.O, Airports.P);
        whenIRecoverTheTravelTime();
        thenIHaveATravelTimeOf(22);
    }

    @Test
    public void scenarioFive() {
        givenTheItinerary(Airports.M, Airports.Q, Airports.P);
        whenIRecoverTheTravelTime();
        thenIHaveATravelTimeOf(-1);
    }

    protected void givenTheItinerary(Airports... airports) {
        itinerary = new ArrayList<>(airports.length);
        for (Airports a : airports) itinerary.add(a);
    }

    private void whenIRecoverTheTravelTime() {
        travelTime = routes.getTravelTime(itinerary);
    }

    private void thenIHaveATravelTimeOf(int expectedTravelTime) {
        assertThat(travelTime, is(expectedTravelTime));
    }

}
