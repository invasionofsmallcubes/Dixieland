package com.bravofly.dixieland.visitor;

import com.bravofly.dixieland.Edge;

public interface Visitor<A>
{
  boolean done();

  void visit(Edge<A> edge);

  int getCounter();

  void increaseStep(int amount);

  void decreaseStep(int amount);
}
