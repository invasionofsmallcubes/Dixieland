package com.bravofly.dixieland;

import org.junit.Before;
import org.junit.Test;

public class ExactTravelTimeTest extends CommonsTest {

    private int maxTravelTime;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void scenarioTen_1() throws Exception {
        givenTheRoute(Airports.O, Airports.O);
        andGivenTheTravelTime(30);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(7);

        /*
        * [O, P, O] -> 16
        * [O, P, O, Q, N, O] -> 25
        * [O, P, O, Q, N, O] -> 25
        * [O, Q, N, O] -> 9
        * [O, Q, N, O, P, O] -> 25
        * [O, Q, N, O, Q, N, O] -> 18
        * [O, Q, N, O, Q, N, O, Q, N, O] -> 27
        * */

     }

    @Test
    public void scenarioTen_2() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(5);

        /*
         * [M, N, O, Q] -> 11
         * [M, P, O, Q] -> 15
         * [M, P, Q] -> 11
         * [M, Q] -> 7
         * [M, Q, N, O, Q] -> 16
        */
    }

    @Test
    public void scenarioTen_3() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(20);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(5);

        /*
         * [M, N, O, Q] -> 11
         * [M, P, O, Q] -> 15
         * [M, P, Q] -> 11
         * [M, Q] -> 7
         * [M, Q, N, O, Q] -> 16
         */
    }

    @Test
    public void scenarioTen_4() throws Exception {
        givenTheRoute(Airports.M, Airports.Q);
        andGivenTheTravelTime(25);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(9);

         /*
          * [M, N, O, P, Q] -> 23
          * [M, N, O, Q] -> 11
          * [M, N, O, Q, N, O, Q] -> 20
          * [M, P, O, Q] -> 15
          * [M, P, O, Q, N, O, Q] -> 24
          * [M, P, Q] -> 11
          * [M, P, Q, N, O, Q] -> 20
          * [M, Q] -> 7
          * [M, Q, N, O, Q] -> 16
         */
    }

    @Test
    public void scenarioTen_5() throws Exception {
        givenTheRoute(Airports.M, Airports.M);
        andGivenTheTravelTime(25);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(0);

        /* 0 routes */
    }

    @Test
    public void scenarioTen_6() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(2);

        /*
         * [Q, N, O, Q] -> 9
         * [Q, N, O, Q, N, O, Q] -> 18
         */
    }

    @Test
    public void scenarioTen_7() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(22);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(3);

        /*
         * [Q, N, O, P, Q] -> 21
         * [Q, N, O, Q] -> 9
         * [Q, N, O, Q, N, O, Q] -> 18
         */
    }

    @Test
    public void scenarioTen_8() throws Exception {
        givenTheRoute(Airports.Q, Airports.Q);
        andGivenTheTravelTime(21);
        whenIRetrieveTotalRoutes();
        thenIGetANumberOfRoutesOf(2);

        /*
         * [Q, N, O, Q] -> 9
         * [Q, N, O, Q, N, O, Q] -> 18
         */
    }

    private void whenIRetrieveTotalRoutes() {
        result = routes.getRoutesWithExactTravelTime(maxTravelTime, departure, arrival);
    }

    private void andGivenTheTravelTime(int maxTravelTime) {
        this.maxTravelTime = maxTravelTime;
    }

}
