package com.bravofly.exercise.main.visitor;

import com.bravofly.exercise.main.Airports;
import com.bravofly.exercise.main.Edge;

public class MaxHopsVisitor extends VisitorImpl
{

  public MaxHopsVisitor(int threshold, Airports target, int currentDepth)
  {
    super(threshold, target, currentDepth);
  }

  @Override
  public void increaseStep(int amount)
  {
    currentStep++;
  }

  @Override
  public void decreaseStep(int amount)
  {
    currentStep--;
  }

  @Override
  public void visit(Edge edge)
  {
    counter += edge.getDestination().equals(target) ? 1 : 0;
  }

}
