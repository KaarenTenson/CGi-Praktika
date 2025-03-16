package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
//this outdated, used this for developing the project
@Service
public class InMemoryFlightService implements FlightService {
    List<Flight> flights;

    public InMemoryFlightService() {
       flights= new ArrayList<>();
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlightById(Long id) {
        for (Flight flight : flights) {
            if (Objects.equals(flight.getId(), id)) {
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
    public List<Flight> getFilteredFlights(int Page, String departure, String destination, Integer minPrice, Integer maxPrice,
                                                LocalDateTime minTime, LocalDateTime maxTime, String sorting, Boolean asc) {
        List<Flight> filteredFlights = new ArrayList<>();
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

    @Override
    public List<int[]> validateTickets(List<int[]> tickets, Flight flight) {
        List<int[]> validTickets=new ArrayList<>();
        for (int[] ticket: tickets){
            Seat seat=flight.getSeat(ticket[0], ticket[1]);
            if(seat==null){continue;}
            if(seat.getIsAvailable()){
                validTickets.add(ticket);
            }
        }
        return validTickets;
    }

    @Override
    public void purchaseTickets(List<int[]> tickets, Flight flight) {
        for (int[] ticket: tickets){
            Seat seat=flight.getSeat(ticket[0], ticket[1]);
            seat.setIsAvailable(false);
        }
    }

    @Override
    public List<String> getAllDestinationsThatContainString(String destination) {
        return List.of();
    }

    @Override
    public List<String> getAllDeparturesThatContainString(String departure) {
        return List.of();
    }
}
