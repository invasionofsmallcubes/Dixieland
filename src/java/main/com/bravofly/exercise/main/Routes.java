package com.bravofly.exercise.main;

import java.util.List;
import java.util.Set;

public class Routes {

    private Set<Flight> flights;

    public Routes(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public int getTravelTime(List<Airports> itinerary) {
        int sum = 0;
        for(int i=0; i<itinerary.size()-1;i++) {
            Flight toSearch = new Flight(itinerary.get(i), itinerary.get(i+1));
            if(!flights.contains(toSearch)) return 0;
            sum += recoverTravelTime(toSearch);
        }
        return sum;
    }

    private int recoverTravelTime(Flight toSearch) {
        for(Flight f: flights) {
            if(f.equals(toSearch)) return f.getTravelTime();
        }
        return 0;
    }
}
