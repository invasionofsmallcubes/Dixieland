package com.bravofly.exercise;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShortestPath extends CommonsTest {
    @Test
    public void testScenarioEight() throws Exception {
        assertThat(routes.getShortestPath(Airports.M, Airports.O), is(9));
    }

    @Test
    public void testScenarioNine() throws Exception {
        assertThat(routes.getShortestPath(Airports.N, Airports.N), is(9));
    }
}
