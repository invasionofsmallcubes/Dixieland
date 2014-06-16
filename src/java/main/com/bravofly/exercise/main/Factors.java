package com.bravofly.exercise.main;

public class Factors
{
  private Airports target;
  private Airports destination;
  private int currentDepth;
  private int maxDepth;

  public Factors(Airports target, Airports destination) {
    this.target = target;
    this.destination = destination;
  }

  public Factors(Airports target, Airports destination, int currentDepth, int maxDepth) {
    this.target = target;
    this.destination = destination;
    this.currentDepth = currentDepth;
    this.maxDepth = maxDepth;
  }

  public Airports getDestination()
  {
    return destination;
  }

  public Airports getTarget()
  {
    return target;
  }

  public int getCurrentDepth()
  {
    return currentDepth;
  }

  public int getMaxDepth()
  {
    return maxDepth;
  }
}
