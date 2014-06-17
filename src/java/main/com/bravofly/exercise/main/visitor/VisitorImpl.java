package com.bravofly.exercise.main.visitor;

import com.bravofly.exercise.main.Airports;

public abstract class VisitorImpl implements Visitor
{
  protected final int threshold;
  protected int counter;
  protected Airports target;
  protected int currentStep;

  public VisitorImpl(int threshold, Airports target, int currentStep)
  {
    this.threshold = threshold;
    this.target = target;
    this.currentStep = currentStep;
  }

  public boolean done()
  {
    return currentStep > threshold;
  }

  public int getCounter()
  {
    return counter;
  }
}
