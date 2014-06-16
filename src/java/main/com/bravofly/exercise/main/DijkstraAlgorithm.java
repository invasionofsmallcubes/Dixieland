package com.bravofly.exercise.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm
{

  private final Graph graph;
  private List<Edge> edges;
  private Set<Airports> settledNodes;
  private Set<Airports> unSettledNodes;
  private Map<Airports, Airports> predecessors;
  private Map<Airports, Integer> distance;

  public DijkstraAlgorithm(Graph graph)
  {
    this.graph = graph;
    this.edges = new ArrayList<>(graph.getEdges());
  }

  public void execute(Airports source)
  {
    settledNodes = new HashSet<>();
    unSettledNodes = new HashSet<>();
    distance = new HashMap<>();
    predecessors = new HashMap<>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0)
    {
      Airports node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }

    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0)
    {
      Airports node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(Airports node)
  {
    List<Airports> adjacentNodes = getNeighbors(node);
    for (Airports target : adjacentNodes)
    {
      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target))
      {
        distance.put(target, getShortestDistance(node) + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }

  }

  private int getDistance(Airports node, Airports target)
  {
    for (Edge edge : edges)
    {
      if (edge.getSource().equals(node) && edge.getDestination().equals(target))
      {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Airports> getNeighbors(Airports node)
  {
    List<Airports> neighbors = new ArrayList<>();
    for (Edge edge : edges)
    {
      if (edge.getSource().equals(node) && !isSettled(edge.getDestination()))
      {
        neighbors.add(edge.getDestination());
      }
    }
    return neighbors;
  }

  private Airports getMinimum(Set<Airports> airportsList)
  {
    Airports minimum = null;
    for (Airports a : airportsList)
    {
      if (minimum == null)
      {
        minimum = a;
      }
      else
      {
        if (getShortestDistance(a) < getShortestDistance(minimum))
        {
          minimum = a;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Airports Airports)
  {
    return settledNodes.contains(Airports);
  }

  private int getShortestDistance(Airports destination)
  {
    Integer d = distance.get(destination);
    if (d == null)
    {
      return Integer.MAX_VALUE;
    }
    else
    {
      return d;
    }
  }

  public List<List<Airports>> getPaths(Airports source,Airports target) {
    List<Edge> inEdges = graph.getInEdges(target);
    List<List<Airports>> paths = new ArrayList<>(inEdges.size());
    for(Edge e: inEdges) {
      paths.add(getPath(e.getSource()));
      paths.get(paths.size()-1).add(source);
    }
    return paths;
  }

  public LinkedList<Airports> getPath(Airports target)
  {
    LinkedList<Airports> path = new LinkedList<>();
    Airports step = target;


    if (predecessors.get(step) == null)
    {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null)
    {
      step = predecessors.get(step);
      path.add(step);
    }
    Collections.reverse(path);
    return path;
  }

} 
