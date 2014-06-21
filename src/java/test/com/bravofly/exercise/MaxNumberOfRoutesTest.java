package com.bravofly.exercise;

import org.junit.Test;

public class MaxNumberOfRoutesTest extends CommonsTest {

    private int numberOfRoutes;

    @Test
    public void scenarioSix_1() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        andGivenTheMaxNumberOfRoutes(2);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(2);
    }

    @Test
    public void scenarioSix_2() throws Exception {
        givenTheRoute(Airports.M, Airports.P);
        andGivenTheMaxNumberOfRoutes(2);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(3);
    }

    @Test
    public void scenarioSix_3() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheMaxNumberOfRoutes(3);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(7);
    }

    @Test
    public void scenarioSix_4() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheMaxNumberOfRoutes(1);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(2);
    }

    @Test
    public void scenarioSix_5() throws Exception {
        givenTheRoute(Airports.M, Airports.O);
        andGivenTheMaxNumberOfRoutes(2);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(3);
    }

    private void andGivenTheMaxNumberOfRoutes(int numberOfRoutes) {
        this.numberOfRoutes = numberOfRoutes;
    }

    private void whenIRetrieveTheRoutes() {
        result = routes.getRoutesWithAtMostHops(numberOfRoutes, departure, arrival);
    }
}
