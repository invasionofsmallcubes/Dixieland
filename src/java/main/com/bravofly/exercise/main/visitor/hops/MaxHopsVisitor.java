package com.bravofly.exercise.main.visitor.hops;

import com.bravofly.exercise.main.Airports;
import com.bravofly.exercise.main.Edge;
import com.bravofly.exercise.main.visitor.hops.HopsVisitor;

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
