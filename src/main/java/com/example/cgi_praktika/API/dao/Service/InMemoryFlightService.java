package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InMemoryFlightService implements FlightDAO {
    ArrayList<Flight> flights = new ArrayList<>();

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlightById(int id) {
        return  flights.get(id);
    }


    @Override
    public Flight getFlightByDestination(String dest) {
        for (Flight flight : flights) {
            if (flight.getDestination().equals(dest)) {
                return flight;
            }
        }
        //muuda exceptioniks
        return null;
    }
}
