package com.example.cgi_praktika.API.dao.DataObjects;

import java.util.List;

public class FlightQuery {
    int pageCount;
    int currentPage;
    List<Flight> flights;

    public FlightQuery(int pageCount, int currentPage, List<Flight> flights) {
        this.pageCount = pageCount;
        this.currentPage = currentPage;
        this.flights = flights;
    }
}
