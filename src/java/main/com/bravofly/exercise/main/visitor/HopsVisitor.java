package com.bravofly.exercise.main.visitor;

import com.bravofly.exercise.main.Airports;

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
