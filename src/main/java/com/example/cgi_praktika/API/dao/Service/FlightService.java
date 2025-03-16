package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FlightService {
    public List<Flight> getAllFlights();
    public Flight getFlightById(Long id);
    public List<Flight> getAllFlightsByDestination(String dest);
    public List<String> getDestinations();
    public List<Flight> getFilteredFlights(int Page, String departure, String destination, Integer minPrice, Integer maxPrice, LocalDateTime minTime, LocalDateTime maxTime, String sorting, Boolean asc);
    public List<int[]> validateTickets(List<int[]> tickets, Flight flight);
    public void purchaseTickets(List<int[]> tickets, Flight flight);
    public List<String> getAllDestinationsThatContainString(String destination);
    public List<String> getAllDeparturesThatContainString(String departure);

}
