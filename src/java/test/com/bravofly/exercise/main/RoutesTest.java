package com.bravofly.exercise.main;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoutesTest {

    private Routes routes = new Routes();

    @Before
    public void setUp() throws Exception {
        routes.add(new Route(Airports.Magenta, Airports.Navy, 5));
    }

    @Test
    public void iCanAddARoute() throws Exception {
        assertThat(routes.size(), is(1));
    }

    @Test
    public void iCanAddTwoRoutes() throws Exception {
        routes.add(new Route(Airports.Magenta, Airports.Navy, 5));
        assertThat(routes.size(), is(2));
    }

    @Test
    public void iCanGetARoute() throws Exception {
        Route expectedRoute = new Route(Airports.Magenta, Airports.Navy, 5);
        Route route = routes.get(expectedRoute);
        assertThat(route, is(expectedRoute));
    }
}
