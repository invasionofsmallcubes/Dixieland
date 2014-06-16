package com.bravofly.exercise.main;

public class ExactHopPredicate implements Predicate
{
  @Override
  public int apply(Object... args)
  {
    Airports target = (Airports) args[0];
    Airports destination = (Airports) args[1];
    int currentDepth = (int) args[2];
    int maxDepth = (int) args[3];
    return target.equals(destination) && (false || currentDepth == maxDepth) ? 1 : 0;
  }
}
