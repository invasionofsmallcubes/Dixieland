package com.bravofly.exercise;

import org.junit.Test;

public class ExactTravelTimeTest extends CommonsTest {

    private int maxTravelTime;

    @Test
    public void scenarioTen_1() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        andGivenTheTravelTime(30);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(7);
    }

    @Test
    public void scenarioTen_2() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(5);
    }

    @Test
    public void scenarioTen_3() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(5);
    }

    @Test
    public void scenarioTen_4() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(25);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(9);
    }

    @Test
    public void scenarioTen_5() throws Exception {
        givenTheRoute(Airports.M, Airports.M);
        andGivenTheTravelTime(25);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(0);
    }

    @Test
    public void scenarioTen_6() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(2);
    }

    @Test
    public void scenarioTen_7() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(22);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(3);
    }

    @Test
    public void scenarioTen_8() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTheTimeTravel();
        thenIGetANumberOfRoutesOf(2);
    }

    private void whenIRetrieveTheTimeTravel() {
        result = routes.getRoutesWithExactTimeInterval(maxTravelTime, departure, arrival);
    }

    private void andGivenTheTravelTime(int maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }

}
