package com.bravofly.dixieland;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExactNumberOfRoutesTest extends CommonsTest {

    private int numberOfRoutes;

    @Test
    public void scenarioSeven_1() throws Exception {
        givenTheRoute(Airports.M, Airports.O);
        andGivenTheExactNumberOfRoutes(3);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(3);
    }

    @Test
    public void scenarioSeven_2() throws Exception {
        givenTheRoute(Airports.M, Airports.O);
        andGivenTheExactNumberOfRoutes(1);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(2);
    }

    @Test
    public void scenarioSeven_3() throws Exception {
        givenTheRoute(Airports.M, Airports.N);
        andGivenTheExactNumberOfRoutes(0);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(1);
    }

    @Test
    public void scenarioSeven_4() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        andGivenTheExactNumberOfRoutes(1);
        whenIRetrieveTheRoutes();
        thenIGetANumberOfRoutesOf(1);
    }

    private void whenIRetrieveTheRoutes() {
        result = routes.getRoutesWithExactHops(numberOfRoutes, departure, arrival);
    }

    private void andGivenTheExactNumberOfRoutes(int numberOfRoutes) {
        this.numberOfRoutes = numberOfRoutes;
    }

}
