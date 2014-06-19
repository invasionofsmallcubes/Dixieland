package com.bravofly.exercise.visitor;

import com.bravofly.exercise.Airports;

public abstract class GenericVisitor implements Visitor
{
  protected final int threshold;
  protected int counter;
  protected Airports target;
  protected int currentStep;

  public GenericVisitor(int threshold, Airports target, int currentStep)
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
