package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;

import java.util.List;

public interface FlightDAO {
    public List<Flight> getAllFlights();
    public Flight getFlightById(int id);
    public Flight getFlightByDestination(String dest);

}
