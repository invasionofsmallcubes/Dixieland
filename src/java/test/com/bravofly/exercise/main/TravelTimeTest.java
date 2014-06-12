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
    private  List<Airports> itinerary = new ArrayList<>();;

    @Before
    public void setUp() throws Exception {

        Set<Flight> flights = new HashSet<>(1);
        flights.add(new Flight(Airports.Magenta,Airports.Navy,5));
        flights.add(new Flight(Airports.Navy,Airports.Orange,6));


        routes = new Routes(flights);
    }

    @Test
    public void testATravelTimeForOneItineraryWithTwoAirports() {
        itinerary.add(Airports.Magenta);
        itinerary.add(Airports.Navy);
        assertTravelTime(itinerary, 5);
    }

    @Test
    public void testATravelTimeForOneItineraryWithTwoAnotherAirports() {
        itinerary.add(Airports.Navy);
        itinerary.add(Airports.Orange);
        assertTravelTime(itinerary, 6);
    }

    @Test
    public void testATravelTimeForWithAChange() {
        itinerary.add(Airports.Magenta);
        itinerary.add(Airports.Navy);
        itinerary.add(Airports.Orange);

        assertTravelTime(itinerary,11) ;
    }

    private void assertTravelTime(List<Airports> itinerary, int expectedTravelTime) {
        assertThat(routes.getTravelTime(itinerary), is(expectedTravelTime));
    }


}
