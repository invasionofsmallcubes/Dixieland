package com.bravofly.dixieland;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EdgeTest
{

  @Test
  public void testHashCode() throws Exception
  {
    assertThat(new Edge<>("A","A",0).hashCode(),is(2080));
  }

  @Test
  public void testToString() throws Exception
  {
    assertThat(new Edge<>("A","A",0).toString(),is("AA0"));
  }
}
