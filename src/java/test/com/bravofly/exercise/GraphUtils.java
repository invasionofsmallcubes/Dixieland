package com.bravofly.exercise;

import java.util.LinkedList;
import java.util.List;

public class GraphUtils
{
  private final Routes routes;
  private final Graph<Airports> graph;

  public GraphUtils(Routes routes, Graph<Airports> graph)
  {
    this.routes = routes;
    this.graph = graph;
  }

  public void getAllPathsUnder(int depth, Airports source, Airports target)
  {

    List<List<Airports>> result = new LinkedList<>();

    getAllPathsUnderAnInterval(new LinkedList<Airports>(), result, 0, depth, source, target);

    for (List<Airports> list : result)
    {
      System.out.println(list + " -> " + routes.getTravelTime(list));
    }

  }

  private void getAllPathsUnderAnInterval(List<Airports> currentAirports, List<List<Airports>> foundPaths,
                                          int currentStep, int depth, Airports source, Airports target)
  {

    if (currentStep > depth)
    {
      return;
    }

    currentAirports.add(source);

    List<Airports> newCurrentAirports;
    for (Edge<Airports> x : graph.getNeighboursOf(source))
    {
      if (x.getDestination().equals(target) && currentStep + x.getWeight() < depth)
      {
        currentAirports.add(x.getDestination());
        foundPaths.add(currentAirports);

        newCurrentAirports = new LinkedList<>();
        for (int i = 0 ; i < currentAirports.size() - 1 ; i++)
        {
          newCurrentAirports.add(currentAirports.get(i));
        }
      }
      else
      {
        newCurrentAirports = new LinkedList<>();
        for (int i = 0 ; i < currentAirports.size() ; i++)
        {
          newCurrentAirports.add(currentAirports.get(i));
        }
      }

      getAllPathsUnderAnInterval(newCurrentAirports, foundPaths, currentStep + x.getWeight(), depth, x.getDestination(),
        target);
    }
  }
}
