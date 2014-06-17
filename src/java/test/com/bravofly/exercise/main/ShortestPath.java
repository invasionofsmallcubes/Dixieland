package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShortestPath extends CommonsTest {
    @Test
    public void testScenarioEight() throws Exception {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph, routes);
        assertThat(dijkstraAlgorithm.getShortestPath(Airports.M, Airports.O), is(9));
    }

    @Test
    public void testScenarioNine() throws Exception {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph, routes);
        assertThat(dijkstraAlgorithm.getShortestPath(Airports.N, Airports.N), is(9));
    }
}
