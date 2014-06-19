package com.bravofly.exercise.visitor.hops;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.visitor.GenericVisitor;

public abstract class HopsVisitor extends GenericVisitor
{

  public HopsVisitor(int threshold, Airports target, int currentStep)
  {
    super(threshold, target, currentStep);
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
}
