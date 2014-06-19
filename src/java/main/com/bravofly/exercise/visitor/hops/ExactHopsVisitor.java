package com.bravofly.exercise.visitor.hops;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.Edge;

public class ExactHopsVisitor extends HopsVisitor
{
  public ExactHopsVisitor(int threshold, Airports target, int currentDepth)
  {
    super(threshold, target, currentDepth);
  }

  @Override
  public void visit(Edge edge)
  {
    counter +=
      ( edge.getDestination().equals(target)
      && currentStep-1 == threshold )
      ? 1 : 0;
  }
}
