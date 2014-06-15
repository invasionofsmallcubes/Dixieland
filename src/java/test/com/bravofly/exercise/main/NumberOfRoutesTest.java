package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberOfRoutesTest extends CommonsTest {

    @Test
    public void maxNumberOfRoutes_scenarioSix() throws Exception {
        Routes r = new Routes(g);
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(2, Airports.Orange, Airports.Orange), is(2));
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(2, Airports.Magenta, Airports.Pink), is(3));
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(3, Airports.Magenta, Airports.Quartz), is(7));
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(1, Airports.Magenta, Airports.Quartz), is(2));
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(2, Airports.Magenta, Airports.Orange), is(3));
    }
}
