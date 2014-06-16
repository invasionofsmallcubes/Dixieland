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

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(Airports.Magenta);
        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Orange);
        assertThat(routes.getTravelTime(path), is(9));
    }

    @Test@Ignore
    public void testScenarioNine() throws Exception {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.execute(Airports.Navy);
        LinkedList<Airports> path = dijkstraAlgorithm.getPath(Airports.Navy);
        assertThat(routes.getTravelTime(path), is(9));
    }

    //
//    private Routes routes;
//
//    @Test@Ignore
//    public void testOneSimplePaths() throws Exception {
//
//        routes = new Routes(null);
//
//        Graph graph = new Graph();
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        graph.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//
//
//        TreeNode startNode = routes.getPaths(graph, Airports.Magenta, Airports.Orange, 11);
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
//        Graph graph = new Graph();
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        graph.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//        graph.addEdge(new GraphNode(Airports.Navy),new Edge(Airports.Orange, 6));
//
//        TreeNode startNode = routes.getPaths(graph, Airports.Magenta, Airports.Orange, 11);
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
//        Graph graph = new Graph();
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Navy, 4));
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Pink, 5));
//        graph.addEdge(new GraphNode(Airports.Magenta),new Edge(Airports.Quartz, 5));
//        graph.addEdge(new GraphNode(Airports.Quartz),new Edge(Airports.Orange, 2));
//        graph.addEdge(new GraphNode(Airports.Orange),new Edge(Airports.Navy, 1));
//        graph.addEdge(new GraphNode(Airports.Pink),new Edge(Airports.Orange, 6));
//        graph.addEdge(new GraphNode(Airports.Navy),new Edge(Airports.Orange, 6));
//
//        TreeNode startNode = routes.getPaths(graph, Airports.Magenta, Airports.Orange, 11);
//        assertThat(startNode.getChildren().size(), is(3));
//        assertThat(startNode.getChildren().get(0).getChildren().size(), is(1));
//        assertThat(startNode.getChildren().get(1).getChildren().size(), is(1));
//        assertThat(startNode.getChildren().get(2).getChildren().size(), is(0));
//    }
}
