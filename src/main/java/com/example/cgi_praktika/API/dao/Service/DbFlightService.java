package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.Repositry.Repositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DbFlightService implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public DbFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(int id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> getAllFlightsByDestination(String dest) {
        return flightRepository.findByDestination(dest);
    }

    @Override
    public List<String> getDestinations() {
        return flightRepository.findAllDestinations();
    }

    @Override
    public List<Flight> getSortedFlights(String destination, String column, Boolean asc) {
        // Sorting dynamically
        List<Flight> flights = flightRepository.findByDestination(destination);
        flights.sort((a, b) -> {
            int order = asc ? 1 : -1;
            switch (column) {
                case "price": return Float.compare(a.getPrice(), b.getPrice()) * order;
                case "departureTime": return a.getDepartureTime().compareTo(b.getDepartureTime()) * order;
                case "flightTime": return Float.compare(a.getFlightTime(), b.getFlightTime()) * order;
                default: return 0;
            }
        });
        return flights;
    }

    @Override
    public List<Flight> getFilteredFlights(int page, String departure, String destination, Integer minPrice, Integer maxPrice, LocalDateTime minTime, LocalDateTime maxTime, String sorting, Boolean asc) {
        List<Flight> flights = flightRepository.filterFlights(departure, destination, minPrice, maxPrice, minTime, maxTime);
        flights.sort((a, b) -> {
            int order = asc ? 1 : -1;
            switch (sorting) {
                case "price": return Float.compare(a.getPrice(), b.getPrice()) * order;
                case "departureTime": return a.getDepartureTime().compareTo(b.getDepartureTime()) * order;
                case "flightTime": return Float.compare(a.getFlightTime(), b.getFlightTime()) * order;
                default: return 0;
            }
        });
        return flights;
    }

    @Override
    public List<int[]> validateTickets(List<int[]> tickets, Flight flight) {
        // For now, just return the input (seat validation logic can be added)
        return tickets;
    }

    @Override
    public void purchaseTickets(List<int[]> tickets, Flight flight) {
        // Placeholder logic (add ticket purchase logic later)
        System.out.println("Tickets purchased for flight ID: " + flight.getId());
    }
}
