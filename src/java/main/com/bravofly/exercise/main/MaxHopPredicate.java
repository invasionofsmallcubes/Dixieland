package com.bravofly.exercise.main;

public class MaxHopPredicate implements Predicate
{
  @Override
  public int apply(Object... args)
  {
    Airports target = (Airports) args[0];
    Airports destination = (Airports) args[1];
    return target.equals(destination) ? 1 : 0;
  }
}
