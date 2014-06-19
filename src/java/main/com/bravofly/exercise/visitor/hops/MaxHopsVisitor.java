package com.bravofly.exercise.visitor.hops;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.Edge;

public class MaxHopsVisitor extends HopsVisitor
{

  public MaxHopsVisitor(int threshold, Airports target, int currentDepth)
  {
    super(threshold, target, currentDepth);
  }

  @Override
  public void visit(Edge edge)
  {
    counter += edge.getDestination().equals(target) ? 1 : 0;
  }

}
