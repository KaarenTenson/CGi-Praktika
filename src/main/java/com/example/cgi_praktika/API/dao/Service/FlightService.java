package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;

import java.util.ArrayList;
import java.util.List;

public interface FlightService {
    public List<Flight> getAllFlights();
    public Flight getFlightById(int id);
    public ArrayList<Flight> getAllFlightsByDestination(String dest);
    public ArrayList<String> getDestinations();
    public ArrayList<Flight> getSortedFlights(String destination, String column, Boolean asc);

}
