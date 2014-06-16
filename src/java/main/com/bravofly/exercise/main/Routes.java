package com.bravofly.exercise.main;

import java.util.List;

public class Routes
{
  private Graph g;

  public Routes(Graph g)
  {
    this.g = g;
  }

  public int getTravelTime(List<Airports> itinerary)
  {
    int sum = 0;
    for (int i = 0 ; i < itinerary.size() - 1 ; i++)
    {
      Edge edgeToSearch = new Edge(itinerary.get(i), itinerary.get(i + 1));
      if (!g.getEdges().contains(edgeToSearch))
      {
        return 0;
      }
      sum += recoverTravelTime(edgeToSearch);
    }
    return sum;
  }

  private int recoverTravelTime(Edge toSearch)
  {
    for (Edge f : g.getEdges())
    {
      if (f.equals(toSearch))
      {
        return f.getWeight();
      }
    }
    return 0;
  }

  private int paths(int maxVertex, Airports s, Airports e, boolean goDeep)
  {
    return depthFirstSearch(0, maxVertex, s, e, goDeep);
  }

  private int depthFirstSearch(int currentDepth, int maxDepth, Airports s, Airports e, boolean goDeep)
  {
    int counter = 0;
    if (currentDepth > maxDepth) return counter;

    int newDepth = currentDepth + 1;
    for (Edge x : g.getNeighboursOf(s))
    {
      counter += ((x.getDestination().equals(e) && (goDeep || (currentDepth == maxDepth)))) ? 1 : 0;
      counter += depthFirstSearch(newDepth, maxDepth, x.getDestination(), e, goDeep);
    }
    return counter;
  }

  public int getPathsWithEqualOrLessThenHops(int maxVertex, Airports source, Airports destination)
  {
    return paths(maxVertex, source, destination, true);
  }

  public int getPathsWithEqualsHops(int maxVertex, Airports source, Airports destination)
  {
    return paths(maxVertex, source, destination, false);
  }

}