package com.bravofly.exercise;

import com.bravofly.exercise.utils.GraphTestUtils;
import org.junit.Before;
import org.junit.Test;

public class ExactTravelTimeTest extends CommonsTest {

    private int maxTravelTime;
    private GraphTestUtils gtu;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        gtu = new GraphTestUtils(routes);
    }

    @Test
    public void scenarioTen_1() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        andGivenTheTravelTime(30);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(7);

        System.out.println("1");
        gtu.printAllPathsUnder(30, Airports.O, Airports.O);
    }

    @Test
    public void scenarioTen_2() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(5);

        System.out.println("2");
        gtu.printAllPathsUnder(20, Airports.M, Airports.Q);
    }

    @Test
    public void scenarioTen_3() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(5);

        System.out.println("3");
        gtu.printAllPathsUnder(20, Airports.M, Airports.Q);
    }

    @Test
    public void scenarioTen_4() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(25);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(9);

        System.out.println("4");
        gtu.printAllPathsUnder(25, Airports.M, Airports.Q);
    }

    @Test
    public void scenarioTen_5() throws Exception {
        givenTheRoute(Airports.M, Airports.M);
        andGivenTheTravelTime(25);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(0);

        System.out.println("5");
        gtu.printAllPathsUnder(25, Airports.M, Airports.M);
    }

    @Test
    public void scenarioTen_6() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(2);

        System.out.println("6");
        gtu.printAllPathsUnder(21, Airports.Q, Airports.Q);
    }

    @Test
    public void scenarioTen_7() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(22);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(3);

        System.out.println("7");
        gtu.printAllPathsUnder(22, Airports.Q, Airports.Q);
    }

    @Test
    public void scenarioTen_8() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(2);

        System.out.println("8");
        gtu.printAllPathsUnder(21, Airports.Q, Airports.Q);
    }

    private void whenIRetrieveTotalRoutes() {
        result = routes.getRoutesWithExactTimeInterval(maxTravelTime, departure, arrival);
    }

    private void andGivenTheTravelTime(int maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }

}
