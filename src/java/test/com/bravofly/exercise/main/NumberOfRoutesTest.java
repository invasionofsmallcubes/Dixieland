package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberOfRoutesTest extends CommonsTest {

    @Test
    public void maxNumberOfRoutes_scenarioSix() throws Exception {
        assertThat(routes.getItinerariesWithLessThenOrEqualsTo(2, Airports.Orange, Airports.Orange), is(2));
        assertThat(routes.getItinerariesWithLessThenOrEqualsTo(2, Airports.Magenta, Airports.Pink), is(3));
        assertThat(routes.getItinerariesWithLessThenOrEqualsTo(3, Airports.Magenta, Airports.Quartz), is(7));
        assertThat(routes.getItinerariesWithLessThenOrEqualsTo(1, Airports.Magenta, Airports.Quartz), is(2));
        assertThat(routes.getItinerariesWithLessThenOrEqualsTo(2, Airports.Magenta, Airports.Orange), is(3));
    }

    @Test
    public void exactNumberOfRoutes_scenarioSeven() throws Exception {
        assertThat(routes.getItinerariesWithLessEqualsTo(3, Airports.Magenta, Airports.Orange), is(3));
        assertThat(routes.getItinerariesWithLessEqualsTo(1, Airports.Magenta, Airports.Orange), is(2));
        assertThat(routes.getItinerariesWithLessEqualsTo(0, Airports.Magenta, Airports.Navy), is(1));
        assertThat(routes.getItinerariesWithLessEqualsTo(1, Airports.Orange, Airports.Orange), is(1));
    }
}
