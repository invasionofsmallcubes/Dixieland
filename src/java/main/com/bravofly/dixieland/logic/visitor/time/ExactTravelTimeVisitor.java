package com.bravofly.dixieland.logic.visitor.time;

import com.bravofly.dixieland.Airports;
import com.bravofly.dixieland.logic.Edge;
import com.bravofly.dixieland.logic.visitor.GenericVisitor;

public class ExactTravelTimeVisitor extends GenericVisitor
{
  public ExactTravelTimeVisitor(int threshold, Airports target, int currentDepth)
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
