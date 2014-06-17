package com.bravofly.exercise.main;

import com.bravofly.exercise.main.visitor.ExactHopsVisitor;
import com.bravofly.exercise.main.visitor.ExactTimeVisitor;
import com.bravofly.exercise.main.visitor.MaxHopsVisitor;
import com.bravofly.exercise.main.visitor.Visitor;

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

  private int depthFirstSearch(int amount, Airports source, Visitor visitor)
  {
    if (visitor.done())
    {
      return visitor.getCounter();
    }

    visitor.increaseStep(amount);

    for (Edge edge : g.getNeighboursOf(source))
    {
      visitor.visit(edge);
      depthFirstSearch(edge.getWeight(), edge.getDestination(), visitor);
    }
    visitor.decreaseStep(amount);

    return visitor.getCounter();
  }

  public int getPathsWithExactTimeInterval(int depth, Airports source, Airports target)
  {
    return depthFirstSearch(0, source, new ExactTimeVisitor(depth, target, 0));
  }

  public int getPathsWithEqualOrLessThenHops(int maxVertex, Airports source, Airports destination)
  {
    return depthFirstSearch(0, source, new MaxHopsVisitor(maxVertex, destination, 0));
  }

  public int getPathsWithEqualsHops(int maxVertex, Airports source, Airports destination)
  {
    return depthFirstSearch(0, source, new ExactHopsVisitor(maxVertex, destination, 0));
  }

  //  public void getAllPathsUnderAnInterval(int depth, Airports source, Airports target)
  //  {
  //
  //    List<List<Airports>> result = new LinkedList<>();
  //
  //    pathsC(new LinkedList<Airports>(), result, 0, depth, source, target);
  //
  //    for (List<Airports> list : result)
  //    {
  //      System.out.println(list + " -> " + getTravelTime(list));
  //    }
  //
  //  }
  //
  //  private void pathsC(List<Airports> currentAirports, List<List<Airports>> foundPaths, int currentStep, int depth,
  //                      Airports source, Airports target)
  //  {
  //
  //    if (currentStep > depth)
  //    {
  //      return;
  //    }
  //
  //    currentAirports.add(source);
  //
  //    List<Airports> newCurrentAirports;
  //    for (Edge x : g.getNeighboursOf(source))
  //    {
  //      if (x.getDestination().equals(target) && currentStep + x.getWeight() < depth)
  //      {
  //        currentAirports.add(x.getDestination());
  //        foundPaths.add(currentAirports);
  //
  //        newCurrentAirports = new LinkedList<>();
  //        for (int i = 0 ; i < currentAirports.size() - 1 ; i++)
  //        {
  //          newCurrentAirports.add(currentAirports.get(i));
  //        }
  //      }
  //      else
  //      {
  //        newCurrentAirports = new LinkedList<>();
  //        for (int i = 0 ; i < currentAirports.size() ; i++)
  //        {
  //          newCurrentAirports.add(currentAirports.get(i));
  //        }
  //      }
  //
  //      pathsC(newCurrentAirports, foundPaths, currentStep + x.getWeight(), depth, x.getDestination(), target);
  //    }
  //  }
}