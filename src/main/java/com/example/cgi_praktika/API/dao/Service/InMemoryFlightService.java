package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class InMemoryFlightService implements FlightDAO {
    ArrayList<Flight> flights = new ArrayList<>();

    public InMemoryFlightService() {
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(new Seat(0, 0, 0.5f, null, null));
        seats.add(new Seat(1, 0, 0.5f, null, null));
        seats.add(new Seat(2, 0, 0.5f, null, null));
        seats.add(new Seat(3, 0, 0.5f, null, null));
        flights.add(new Flight("Tartu", new Timestamp(1003, 20, 30, 2, 1, 2, 2),30, 30,seats));
    }

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
