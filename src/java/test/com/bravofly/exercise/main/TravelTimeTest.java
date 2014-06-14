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
