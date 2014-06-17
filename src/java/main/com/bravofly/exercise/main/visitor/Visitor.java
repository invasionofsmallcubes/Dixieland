package com.bravofly.exercise.main.visitor;

import com.bravofly.exercise.main.Edge;

public interface Visitor
{
  boolean done();

  void visit(Edge edge);

  int getCounter();

  void increaseStep(int amount);

  void decreaseStep(int amount);
}
