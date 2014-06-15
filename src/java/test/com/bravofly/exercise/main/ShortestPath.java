package com.bravofly.exercise.main;

// A simple path is a path with no repeated nodes

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShortestPath extends CommonsTest {
    @Test
    public void testScenarioEight() throws Exception {
        Routes r = new Routes(g);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(g);

        dijkstraAlgorithm.execute(Airports.Magenta);

        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Orange);

        assertThat(r.getTravelTime(path), is(9));
    }

    @Test@Ignore
    public void testScenarioNine() throws Exception {
        Routes r = new Routes(g);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(g);

        dijkstraAlgorithm.execute(Airports.Navy);

        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Navy);

        assertThat(r.getTravelTime(path), is(9));
    }

    //
//    private Routes routes;
//
//    @Test@Ignore
//    public void testOneSimplePaths() throws Exception {
//
//        routes = new Routes(null);
//
//        Graph g = new Graph();
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        g.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//
//
//        TreeNode startNode = routes.getPaths(g, Airports.Magenta, Airports.Orange, 11);
//        assertThat(startNode.getChildren().size(), is(2));
//        assertThat(startNode.getChildren().get(0).getChildren().size(), is(0));
//        assertThat(startNode.getChildren().get(1).getChildren().size(), is(1));
//    }
//
//    @Test@Ignore
//    public void testTwoSimplePaths() throws Exception {
//
//        routes = new Routes(null);
//
//        Graph g = new Graph();
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        g.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//        g.addEdge(new GraphNode(Airports.Navy),new Edge(Airports.Orange, 6));
//
//        TreeNode startNode = routes.getPaths(g, Airports.Magenta, Airports.Orange, 11);
//        assertThat(startNode.getChildren().size(), is(2));
//        assertThat(startNode.getChildren().get(0).getChildren().size(), is(1));
//        assertThat(startNode.getChildren().get(1).getChildren().size(), is(1));
//    }
//
//    @Test
//    public void testThreeSimplePaths() throws Exception {
//
//        routes = new Routes(null);
//
//        Graph g = new Graph();
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        g.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Quartz, 5));
//        g.addEdge(new GraphNode(Airports.Quartz),new Edge(Airports.Orange, 2));
//        g.addEdge(new GraphNode(Airports.Orange),new Edge(Airports.Navy, 1));
//        g.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//        g.addEdge(new GraphNode(Airports.Navy),new Edge(Airports.Orange, 6));
//
//        TreeNode startNode = routes.getPaths(g, Airports.Magenta, Airports.Orange, 11);
//        assertThat(startNode.getChildren().size(), is(3));
//        assertThat(startNode.getChildren().get(0).getChildren().size(), is(1));
//        assertThat(startNode.getChildren().get(1).getChildren().size(), is(1));
//        assertThat(startNode.getChildren().get(2).getChildren().size(), is(0));
//    }
}
