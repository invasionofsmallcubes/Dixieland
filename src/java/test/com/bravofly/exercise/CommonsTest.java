package com.bravofly.exercise;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommonsTest {

    protected Routes routes;
    protected int result;
    protected Airports departure;
    protected Airports arrival;

    @Before
    public void setUp() {
        routes = new Routes();

        routes.addEdge(new Edge<>(Airports.M, Airports.N, 5));
        routes.addEdge(new Edge<>(Airports.N, Airports.O, 4));
        routes.addEdge(new Edge<>(Airports.O, Airports.P, 8));
        routes.addEdge(new Edge<>(Airports.P, Airports.O, 8));
        routes.addEdge(new Edge<>(Airports.P, Airports.Q, 6));
        routes.addEdge(new Edge<>(Airports.M, Airports.P, 5));
        routes.addEdge(new Edge<>(Airports.O, Airports.Q, 2));
        routes.addEdge(new Edge<>(Airports.Q, Airports.N, 3));
        routes.addEdge(new Edge<>(Airports.M, Airports.Q, 7));
    }

    protected void assertExpectation(int expectedResult) {
        assertThat(result, is(expectedResult));
    }

    protected void givenTheRoute(Airports departure, Airports arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    protected void thenIGetANumberOfRoutesOf(int expectedTravelTime) {
        assertExpectation(expectedTravelTime);
    }
}
