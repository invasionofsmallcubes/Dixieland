package com.bravofly.exercise.printer;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.CommonsTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoutesPrinterTest extends CommonsTest {

    RoutesPrinter routesPrinter;

    @Before
    public void setUp() {
        super.setUp();
       routesPrinter = new RoutesPrinter(routes);
    }

    @Test
    public void scenarioOne() throws Exception {
        List<Airports> list = new ArrayList<>();
        list.add(Airports.M);
        list.add(Airports.N);
        list.add(Airports.O);

        assertThat(routesPrinter.getTravelTime(list), is("9"));
    }

    @Test
    public void scenarioTwo() throws Exception {
        List<Airports> list = new ArrayList<>();
        list.add(Airports.M);
        list.add(Airports.P);

        assertThat(routesPrinter.getTravelTime(list), is("5"));
    }

    @Test
    public void scenarioThree() throws Exception {
        List<Airports> list = new ArrayList<>();
        list.add(Airports.M);
        list.add(Airports.P);
        list.add(Airports.O);

        assertThat(routesPrinter.getTravelTime(list), is("13"));
    }

    @Test
    public void scenarioFour() throws Exception {
        List<Airports> list = new ArrayList<>();
        list.add(Airports.M);
        list.add(Airports.Q);
        list.add(Airports.N);
        list.add(Airports.O);
        list.add(Airports.P);

        assertThat(routesPrinter.getTravelTime(list), is("22"));
    }

    @Test
    public void scenarioFive() throws Exception {
        List<Airports> list = new ArrayList<>();
        list.add(Airports.M);
        list.add(Airports.Q);
        list.add(Airports.P);

        assertThat(routesPrinter.getTravelTime(list), is("Solution not found!"));
    }

    @Test
    public void scenarioSix() throws Exception {
        assertThat(routesPrinter.getNumberOfRoutesWithMaxHops(2, Airports.O, Airports.O), is("2"));
    }

    @Test
    public void scenarioSeven() throws Exception {
        assertThat(routesPrinter.getNumberOfRoutesWithExactHops(3, Airports.M, Airports.O), is("3"));
    }

    @Test
    public void scenarioEight() throws Exception {
        assertThat(routesPrinter.getFastestTravelTime(Airports.M, Airports.O), is("9"));
    }

    @Test
    public void scenarioNine() throws Exception {
        assertThat(routesPrinter.getFastestTravelTime(Airports.N, Airports.N), is("9"));
    }

    @Test
    public void scenarioTen() throws Exception {
        assertThat(routesPrinter.getNumberOfRoutesWithExactTravelTime(30,Airports.O,Airports.O), is("7"));
    }
}
