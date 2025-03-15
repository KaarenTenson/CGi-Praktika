package com.example.cgi_praktika.API.dao.DataObjects;

import java.util.ArrayList;

public class FlightQuery {
    int pageCount;
    int currentPage;
    ArrayList<Flight> flights;

    public FlightQuery(int pageCount, int currentPage, ArrayList<Flight> flights) {
        this.pageCount = pageCount;
        this.currentPage = currentPage;
        this.flights = flights;
    }
}
