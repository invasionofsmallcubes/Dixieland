package com.bravofly.exercise;

import com.bravofly.exercise.logic.DepthFirstSearch;
import com.bravofly.exercise.logic.DijkstraAlgorithm;
import com.bravofly.exercise.visitor.hops.ExactHopsVisitor;
import com.bravofly.exercise.visitor.time.ExactTimeVisitor;
import com.bravofly.exercise.visitor.hops.MaxHopsVisitor;

import java.util.List;

public class Routes
{
  private final DepthFirstSearch<Airports> dfs;
  private Graph<Airports> g;
  private DijkstraAlgorithm<Airports> d;

  public Routes(Graph<Airports> g)
  {
    this.g = g;
    d = new DijkstraAlgorithm<>(g);
    dfs = new DepthFirstSearch<>();
  }

  public int getTravelTime(List<Airports> itinerary)
  {
    int sum = 0;
    for (int i = 0 ; i < itinerary.size() - 1 ; i++)
    {
      Edge<Airports> edgeToSearch = new Edge<>(itinerary.get(i), itinerary.get(i + 1));
      if (!g.getEdges().contains(edgeToSearch))
      {
        return 0;
      }
      sum += recoverTravelTime(edgeToSearch);
    }
    return sum;
  }

  public int getPathsWithExactTimeInterval(int depth, Airports source, Airports target)
  {
    return dfs.apply(g, 0, source, new ExactTimeVisitor(depth, target, 0));
  }

  public int getPathsWithEqualOrLessThenHops(int maxVertex, Airports source, Airports destination)
  {
    return dfs.apply(g, 0, source, new MaxHopsVisitor(maxVertex, destination, 0));
  }

  public int getPathsWithEqualsHops(int maxVertex, Airports source, Airports destination)
  {
    return dfs.apply(g, 0, source, new ExactHopsVisitor(maxVertex, destination, 0));
  }

  public int getShortestPath(Airports m, Airports o)
  {
    List<List<Airports>> shortestPath = d.getShortestPath(m, o);

    return getMinPathTravelTime(shortestPath);
  }

  private int recoverTravelTime(Edge<Airports> toSearch)
  {
    for (Edge<Airports> f : g.getEdges())
    {
      if (f.equals(toSearch))
      {
        return f.getWeight();
      }
    }
    return 0;
  }

  private int getMinPathTravelTime(List<List<Airports>> shortestPath)
  {
    int min = Integer.MAX_VALUE;
    for(List<Airports> path :shortestPath) {
      int t = getTravelTime(path);
      if(t < min) {
        min = t;
      }
    }
    return min;
  }


}