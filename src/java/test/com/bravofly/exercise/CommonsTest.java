package com.bravofly.exercise;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class CommonsTest {

    protected Routes routes;
    protected Graph<Airports> graph;

    @Before
    public void setUp() {

        List<Edge<Airports>> edgesList = new ArrayList<>(9);
        edgesList.add(new Edge<>("MN5", Airports.M, Airports.N, 5));
        edgesList.add(new Edge<>("NO4", Airports.N, Airports.O, 4));
        edgesList.add(new Edge<>("OP8", Airports.O, Airports.P, 8));
        edgesList.add(new Edge<>("PO8", Airports.P, Airports.O, 8));
        edgesList.add(new Edge<>("PQ6", Airports.P, Airports.Q, 6));
        edgesList.add(new Edge<>("MP5", Airports.M, Airports.P, 5));
        edgesList.add(new Edge<>("OQ2", Airports.O, Airports.Q, 2));
        edgesList.add(new Edge<>("QN3", Airports.Q, Airports.N, 3));
        edgesList.add(new Edge<>("MQ7", Airports.M, Airports.Q, 7));

        graph = new Graph<>(edgesList);

        routes = new Routes(graph);
    }
}
