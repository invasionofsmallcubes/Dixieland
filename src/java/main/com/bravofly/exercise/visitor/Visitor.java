package com.bravofly.exercise.visitor;

import com.bravofly.exercise.Edge;

public interface Visitor<A>
{
  boolean done();

  void visit(Edge<A> edge);

  int getCounter();

  void increaseStep(int amount);

  void decreaseStep(int amount);
}
