package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RouteTest
{
  @Test
  public void iCanCreateAnEdge() {
      Route route = new Route(Airports.Magenta, Airports.Navy, 5);
      assertThat(route.getSource(),is(Airports.Magenta));
      assertThat(route.getDestination(),is(Airports.Navy));
      assertThat(route.getTravelTime(),is(5));
  }
}
