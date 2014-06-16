package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberOfRoutesTest extends CommonsTest {

    @Test
    public void maxNumberOfRoutes_scenarioSix() throws Exception {
        assertMaxNumberOfRoutes(2, Airports.Orange, Airports.Orange, 2);
        assertMaxNumberOfRoutes(2, Airports.Magenta, Airports.Pink, 3);
        assertMaxNumberOfRoutes(3, Airports.Magenta, Airports.Quartz, 7);
        assertMaxNumberOfRoutes(1, Airports.Magenta, Airports.Quartz, 2);
        assertMaxNumberOfRoutes(2, Airports.Magenta, Airports.Orange, 3);
    }

    @Test
    public void exactNumberOfRoutes_scenarioSeven() throws Exception {
        assertExactNumberOfRoutes(3, Airports.Magenta, Airports.Orange, 3);
        assertExactNumberOfRoutes(1, Airports.Magenta, Airports.Orange, 2);
        assertExactNumberOfRoutes(0, Airports.Magenta, Airports.Navy, 1);
        assertExactNumberOfRoutes(1, Airports.Orange, Airports.Orange, 1);
    }

    @Test
    public void exactTimeInterval_scenarioTen() throws Exception {
        assertExactTimeInterval(30, Airports.Orange, Airports.Orange, 7);
        assertExactTimeInterval(12, Airports.Magenta, Airports.Quartz, 3);
        assertExactTimeInterval(20, Airports.Magenta, Airports.Quartz, 5);
        assertExactTimeInterval(25, Airports.Magenta, Airports.Quartz, 9);
        assertExactTimeInterval(25, Airports.Magenta, Airports.Magenta, 0);
        assertExactTimeInterval(21, Airports.Quartz, Airports.Quartz, 2);
        assertExactTimeInterval(22, Airports.Quartz, Airports.Quartz, 3);
        assertExactTimeInterval(21, Airports.Quartz, Airports.Quartz, 2);
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
