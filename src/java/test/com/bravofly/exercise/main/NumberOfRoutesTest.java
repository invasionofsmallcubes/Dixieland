package com.bravofly.exercise.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberOfRoutesTest extends CommonsTest {

    @Test
    public void maxNumberOfRoutes() throws Exception {

        Routes r = new Routes(g);
        Airports source = Airports.Orange;
        Airports destination = Airports.Orange;
        assertThat(r.getItinerariesWithLessThenOrEqualsTo(2, source, destination), is(2));

    }
}
