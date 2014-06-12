package com.bravofly.exercise.main;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class RoutesTest {

    private Routes routes;

    @Test
    public void iCanAddAListOfRoutes() throws Exception {
        Set<Flight> routesList = new HashSet<>();
        routesList.add(new Flight(Airports.Magenta, Airports.Navy, 5));
        routes = new Routes(routesList);
        assertThat(routes.getFlights(), is(not(nullValue())));
    }

}
