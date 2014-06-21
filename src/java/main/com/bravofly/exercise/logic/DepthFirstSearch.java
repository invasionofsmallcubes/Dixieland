package com.bravofly.exercise.logic;

import com.bravofly.exercise.Edge;
import com.bravofly.exercise.Graph;
import com.bravofly.exercise.visitor.Visitor;

public class DepthFirstSearch<A>
{
  public int apply(Graph<A> g, int amount, A source, Visitor visitor)
  {
    if (visitor.done())
    {
      return visitor.getCounter();
    }

    visitor.increaseStep(amount);

    for (Edge<A> edge : g.getOutEdges(source))
    {
      visitor.visit(edge);
      apply(g, edge.getWeight(), edge.getDestination(), visitor);
    }
    visitor.decreaseStep(amount);

    return visitor.getCounter();
  }
}
