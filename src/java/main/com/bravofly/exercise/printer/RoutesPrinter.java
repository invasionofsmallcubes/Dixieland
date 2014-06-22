package com.bravofly.exercise.printer;

import com.bravofly.exercise.Airports;
import com.bravofly.exercise.Routes;

import java.util.List;

public class RoutesPrinter {

    private Routes routes;

    public RoutesPrinter(Routes routes) {
        this.routes = routes;
    }

    public String getTravelTime(List<Airports> itinerary) {
        int t = routes.getTravelTime(itinerary);
        return t == -1 ? "Solution not found!" : Integer.toString(t);
    }

    public String getNumberOfRoutesWithMaxHops(int hops, Airports departure, Airports arrival) {
        return Integer.toString(routes.getRoutesWithAtMostHops(hops, departure, arrival));
    }

    public String getNumberOfRoutesWithExactHops(int hops, Airports departure, Airports arrival) {
        return Integer.toString(routes.getRoutesWithExactHops(hops,departure,arrival));
    }

    public String getFastestTravelTime(Airports departure, Airports arrival) {
        return Integer.toString(routes.getFastestTravelTime(departure, arrival));
    }

    public String getNumberOfRoutesWithExactTravelTime(int travelTime, Airports departure, Airports arrival) {
        return Integer.toString(routes.getRoutesWithExactTravelTime(travelTime, departure, arrival));
    }
}
