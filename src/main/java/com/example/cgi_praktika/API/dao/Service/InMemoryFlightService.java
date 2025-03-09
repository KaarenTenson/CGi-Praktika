package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataGeneration.GenerateFlights;
import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InMemoryFlightService implements FlightService {
    ArrayList<Flight> flights = new ArrayList<>();

    public InMemoryFlightService() {
       flights= GenerateFlights.generateFlights();
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

    @Override
    public List<Flight> getFilteredFlights(int Page, String departure, String destination, Integer minPrice, Integer maxPrice,
                                                LocalDateTime minTime, LocalDateTime maxTime, String sorting, Boolean asc) {
        ArrayList<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if(departure!=null)
                if(!flight.getDeparture().equals(departure)){continue;}
            if(destination!=null)
                if(!flight.getDestination().equals(destination)){continue;}
            if(minPrice!=null)
                if(flight.getPrice()<minPrice){continue;}
            if(maxPrice!=null)
                if(flight.getPrice()>maxPrice){continue;}
            if(minTime!=null)
                if (flight.getDepartureTime().isBefore(minTime)){continue;}
            if(maxTime!=null)
                if (flight.getDepartureTime().isAfter(minTime)){continue;}
            filteredFlights.add(flight);
        }
        if(sorting==null){
            return filteredFlights;
        }
        switch (sorting){
            case "departure":
                filteredFlights.sort(Comparator.comparing(Flight::getDeparture));
                break;
            case "destination":
                filteredFlights.sort(Comparator.comparing(Flight::getDestination));
                break;
            case "price":
                filteredFlights.sort(Comparator.comparing(Flight::getPrice));
                break;
            case "departureTime":
                filteredFlights.sort(Comparator.comparing(Flight::getDepartureTime));
                break;
            case "flightTime":
                filteredFlights.sort(Comparator.comparing(Flight::getFlightTime));
        }
        if(!asc){
            return filteredFlights.reversed();
        }

        return filteredFlights;
    }
}
