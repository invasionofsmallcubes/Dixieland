package com.bravofly.exercise.main;

import java.util.ArrayList;
import java.util.List;

public class Routes {

    private List<Route> routes = new ArrayList<Route>();

    public void add(Route route) {
        routes.add(route);
    }

    public int size() {
        return routes.size();
    }

    public Route get(Route route) {
        return routes.get(routes.indexOf(route));
    }
}
