package com.bravofly.dixieland.logic;

import java.util.ArrayList;
import java.util.List;

public class Graph<A>
{
  protected List<Edge<A>> edges = new ArrayList<>();

  public void addEdge(Edge<A> edge)
  {
    edges.add(edge);
  }

  public List<Edge<A>> getOutEdges(A vertex)
  {
    List<Edge<A>> list = new ArrayList<>();
    for (Edge e : edges)
    {
      if (e.getSource().equals(vertex))
      {
        list.add(e);
      }
    }
    return list;
  }

  public List<Edge<A>> getInEdges(A vertex)
  {
    List<Edge<A>> list = new ArrayList<>();
    for (Edge<A> e : edges)
    {
      if (e.getDestination().equals(vertex))
      {
        list.add(e);
      }
    }
    return list;
  }

}