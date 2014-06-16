package com.bravofly.exercise.main;

import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonsTest {

    protected Routes routes;
    protected Graph graph;

    @Before
    public void setUp() {

        List<Airports> airportsList = Arrays.asList(Airports.values());

        List<Edge> edgesList = new ArrayList<>(9);
        edgesList.add(new Edge("MN5", Airports.Magenta, Airports.Navy, 5));
        edgesList.add(new Edge("NO4", Airports.Navy, Airports.Orange, 4));
        edgesList.add(new Edge("OP8", Airports.Orange, Airports.Pink, 8));
        edgesList.add(new Edge("PO8", Airports.Pink, Airports.Orange, 8));
        edgesList.add(new Edge("PQ6", Airports.Pink, Airports.Quartz, 6));
        edgesList.add(new Edge("MP5", Airports.Magenta, Airports.Pink, 5));
        edgesList.add(new Edge("OQ2", Airports.Orange, Airports.Quartz, 2));
        edgesList.add(new Edge("QN3", Airports.Quartz, Airports.Navy, 3));
        edgesList.add(new Edge("MQ7", Airports.Magenta, Airports.Quartz, 7));

        graph = new Graph(airportsList, edgesList);

        routes = new Routes(graph);
    }
}
