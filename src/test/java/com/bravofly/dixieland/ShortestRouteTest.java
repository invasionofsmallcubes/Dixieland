package com.bravofly.dixieland;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShortestRouteTest extends CommonsTest {

    @Test
    public void testScenarioEight_1() throws Exception {
        givenTheRoute(Airports.M, Airports.O);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(9);
    }

    @Test
    public void testScenarioEight_2() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(7);
    }

    @Test
    public void testScenarioEight_3() throws Exception {
        givenTheRoute(Airports.M, Airports.N);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(5);
    }

    @Test
    public void testScenarioEight_4() throws Exception {
        givenTheRoute(Airports.N, Airports.P);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(12);
    }

    @Test
    public void testScenarioNine_1() throws Exception {
        givenTheRoute(Airports.N, Airports.N);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(9);
    }

    @Test
    public void testScenarioNine_2() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(9);
    }

    @Test
    public void testScenarioNine_3() throws Exception {
        givenTheRoute(Airports.P, Airports.P);
        whenIGetTheShortestRoute();
        thenIFindATravelTimeOf(16);
    }

    private void whenIGetTheShortestRoute() {
        result = routes.getFastestTravelTime(departure, arrival);
    }

    private void thenIFindATravelTimeOf(int expectedTravelTime) {
        thenIGetANumberOfRoutesOf(expectedTravelTime);
    }
}
