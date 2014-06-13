package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FlightTest
{
  @Test
  public void iCanCreateAnEdge() {
      Flight flight = new Flight(Airports.Magenta, Airports.Navy, 5);
      assertThat(flight.getSource(),is(Airports.Magenta));
      assertThat(flight.getDestination(),is(Airports.Navy));
      assertThat(flight.getTravelTime(),is(5));
  }
}
