package com.bravofly.exercise.visitor.time;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.Edge;
import com.bravofly.exercise.visitor.GenericVisitor;

public class ExactTimeVisitor extends GenericVisitor
{
  public ExactTimeVisitor(int threshold, Airports target, int currentDepth)
  {
    super(threshold, target, currentDepth);
  }

  @Override
  public void increaseStep(int amount)
  {
    currentStep+=amount;
  }

  @Override
  public void decreaseStep(int amount)
  {
    currentStep-=amount;
  }

  @Override
  public void visit(Edge edge)
  {
    counter += edge.getDestination().equals(target) && currentStep + edge.getWeight() < threshold ? 1 : 0;
  }
}
