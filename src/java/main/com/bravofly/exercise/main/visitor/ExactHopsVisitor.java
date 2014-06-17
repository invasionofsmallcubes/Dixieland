package com.bravofly.exercise.main.visitor;

import com.bravofly.exercise.main.Airports;
import com.bravofly.exercise.main.Edge;

public class ExactHopsVisitor extends VisitorImpl
{
  public ExactHopsVisitor(int threshold, Airports target, int currentDepth)
  {
    super(threshold, target, currentDepth);
  }

  @Override
  public void increaseStep(int amount)
  {
    currentStep += 1;
  }

  @Override
  public void decreaseStep(int amount)
  {
    currentStep-=1;
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
