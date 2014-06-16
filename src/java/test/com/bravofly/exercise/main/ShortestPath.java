package com.bravofly.exercise.main;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShortestPath extends CommonsTest {
    @Test
    public void testScenarioEight() throws Exception {

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(Airports.Magenta);
        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Orange);
        assertThat(routes.getTravelTime(path), is(9));
    }

    @Test
    public void testScenarioNine() throws Exception {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(Airports.Navy);
        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Navy);
        assertThat(routes.getTravelTime(path), is(9));
    }

}
