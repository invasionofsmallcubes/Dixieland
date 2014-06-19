package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberOfRoutesTest extends CommonsTest {

    @Test
    public void maxNumberOfRoutes_scenarioSix() throws Exception {
        assertMaxNumberOfRoutes(2, Airports.O, Airports.O, 2);
        assertMaxNumberOfRoutes(2, Airports.M, Airports.P, 3);
        assertMaxNumberOfRoutes(3, Airports.M, Airports.Q, 7);
        assertMaxNumberOfRoutes(1, Airports.M, Airports.Q, 2);
        assertMaxNumberOfRoutes(2, Airports.M, Airports.O, 3);
    }

    @Test
    public void exactNumberOfRoutes_scenarioSeven() throws Exception {
        assertExactNumberOfRoutes(3, Airports.M, Airports.O, 3);
        assertExactNumberOfRoutes(1, Airports.M, Airports.O, 2);
        assertExactNumberOfRoutes(0, Airports.M, Airports.N, 1);
        assertExactNumberOfRoutes(1, Airports.O, Airports.O, 1);
    }

    @Test
    public void exactTimeInterval_scenarioTen() throws Exception {
        assertExactTimeInterval(30, Airports.O, Airports.O, 7);
        assertExactTimeInterval(12, Airports.M, Airports.Q, 3);
        assertExactTimeInterval(20, Airports.M, Airports.Q, 5);
        assertExactTimeInterval(25, Airports.M, Airports.Q, 9);
        assertExactTimeInterval(25, Airports.M, Airports.M, 0);
        assertExactTimeInterval(21, Airports.Q, Airports.Q, 2);
        assertExactTimeInterval(22, Airports.Q, Airports.Q, 3);
        assertExactTimeInterval(21, Airports.Q, Airports.Q, 2);
    }

    private void assertExactTimeInterval(int depth, Airports source, Airports target, int expectedResult) throws Exception {
        assertThat(routes.getPathsWithExactTimeInterval(depth, source, target), is(expectedResult));
    }

    private void assertExactNumberOfRoutes(int depth, Airports source, Airports target, int expectedResult) {
        assertThat(routes.getPathsWithEqualsHops(depth, source, target), is(expectedResult));
    }

    private void assertMaxNumberOfRoutes(int depth, Airports source, Airports target, int expectedResult) {
        assertThat(routes.getPathsWithEqualOrLessThenHops(depth, source, target), is(expectedResult));
    }
}
