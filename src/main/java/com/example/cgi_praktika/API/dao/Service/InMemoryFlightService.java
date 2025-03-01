package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class InMemoryFlightService implements FlightService {
    ArrayList<Flight> flights = new ArrayList<>();

    public InMemoryFlightService() {
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(new Seat(0, 0, 0.5f, null, null));
        seats.add(new Seat(1, 0, 0.5f, null, null));
        seats.add(new Seat(2, 0, 0.5f, null, null));
        seats.add(new Seat(3, 0, 0.5f, null, null));
        flights.add(new Flight("Tartu", new Timestamp(1003, 20, 30, 2, 1, 2, 2),30, 30,seats));
        flights.add(new Flight("Tartu", new Timestamp(1003, 20, 30, 2, 1, 2, 2),29, 31,seats));
        flights.add(new Flight("Tallinn", new Timestamp(1003, 20, 30, 2, 1, 2, 2),29, 31,seats));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlightById(int id) {
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }



    @Override
    public ArrayList<Flight> getAllFlightsByDestination(String dest) {
        System.out.println("getting all flights by destination " + dest);
        ArrayList<Flight> flightsByDestination = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDestination().equals(dest)) {
                flightsByDestination.add(flight);
            }
        }
        return flightsByDestination;
    }

    @Override
    public ArrayList<String> getDestinations() {
        HashSet<String> destinations = new HashSet<>();
        for (Flight flight : flights) {
            destinations.add(flight.getDestination());
        }
        return new ArrayList<>(destinations);
    }

    @Override
    public ArrayList<Flight> getSortedFlights(String destination, String column, Boolean asc) {
        ArrayList<Flight> sortedFlights = getAllFlightsByDestination(destination);
        switch (column){
            case "flightTime":
                sortedFlights.sort(Comparator.comparing(Flight::getFlightTime));
                break;
            case "departureTime":
                sortedFlights.sort(Comparator.comparing(Flight::getDepartureTime));
                break;
            case "price":
                sortedFlights.sort(Comparator.comparing(Flight::getPrice));
                break;

            default:
                System.out.println("error sorting flights");
                break;
        }

        return sortedFlights;
    }
}
