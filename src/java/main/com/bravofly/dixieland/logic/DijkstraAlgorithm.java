package com.bravofly.dixieland.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm<A>
{

  private Graph<A> graph;
  private Set<A> exploredNodes = new HashSet<>();
  private Set<A> unexploredNodes = new HashSet<>();
  private Map<A, A> predecessors = new HashMap<>();
  private Map<A, Integer> distance = new HashMap<>();

  public DijkstraAlgorithm(Graph<A> graph)
  {
    this.graph = graph;
  }

  public List<List<A>> getShortestPathForSameArrival(A source)
  {
    execute(source);
    List<List<A>> paths = new ArrayList<>();
    for (Edge<A> e : graph.getInEdges(source))
    {
      List<A> path = getMinimumPath(e.getSource());
      if (path != null)
      {
        paths.add(path);
        paths.get(paths.size() - 1).add(source);
      }
    }
    return paths;
  }

  public List<A> getShortestPathForDifferentArrival(A source, A target)
  {
    execute(source);
    return getMinimumPath(target);
  }

  private void execute(A source)
  {
    distance.put(source, 0);
    unexploredNodes.add(source);
    while (unexploredNodes.size() > 0)
    {
      A node = getMinimum(unexploredNodes);
      exploredNodes.add(node);
      unexploredNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(A source)
  {
    for (Edge<A> edge : graph.getOutEdges(source))
    {
      A target = edge.getDestination();
      if (getShortestDistance(target) > getShortestDistance(source) + getDistance(source, target))
      {
        distance.put(target, getShortestDistance(source) + getDistance(source, target));
        predecessors.put(target, source);
        unexploredNodes.add(target);
      }
    }

  }

  private int getDistance(A source, A target)
  {
    for (Edge edge : graph.getOutEdges(source))
    {
      if (edge.getDestination().equals(target))
      {
        return edge.getWeight();
      }
    }
    return 0;
  }

  private A getMinimum(Set<A> unexploredNodes)
  {
    A minimum = null;
    for (A a : unexploredNodes)
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

  private int getShortestDistance(A destination)
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

  private List<A> getMinimumPath(A target)
  {
    LinkedList<A> path = new LinkedList<>();
    A step = target;

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
