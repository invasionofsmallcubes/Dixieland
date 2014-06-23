package com.bravofly.dixieland.logic.visitor.hops;

import com.bravofly.dixieland.Airports;
import com.bravofly.dixieland.logic.Edge;

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
