package com.bravofly.dixieland.logic.visitor;

import com.bravofly.dixieland.logic.Edge;

public interface Visitor
{
  boolean done();

  void visit(Edge edge);

  int getCounter();

  void increaseStep(int amount);

  void decreaseStep(int amount);
}
