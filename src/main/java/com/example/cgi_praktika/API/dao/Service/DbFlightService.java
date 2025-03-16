package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataGeneration.GenerateFlights;
import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import com.example.cgi_praktika.API.dao.Repositry.FlightRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DbFlightService implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public DbFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
        if(flightRepository.findAll().isEmpty()) {
            GenerateFlights.generateFlights(flightRepository);
        }

    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findByIdWithSeats(id).orElse(null);
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
    public List<Flight> getFilteredFlights(int page, String departure, String destination, Integer minPrice, Integer maxPrice, LocalDateTime minTime, LocalDateTime maxTime, String sorting, Boolean asc) {
        List<Flight> flights;
        if(sorting!=null){
        Sort sort = (asc) ? Sort.by(sorting).ascending() : Sort.by(sorting).descending();
            flights = flightRepository.filterFlights(departure, destination, minPrice, maxPrice, minTime, maxTime, sort);
        }else{
            flights = flightRepository.filterFlights(departure, destination, minPrice, maxPrice, minTime, maxTime, null);
        }

        return flights;
    }
    //checks if chosen tickets are valid(tickets exist and are available)
    @Override
    public List<int[]> validateTickets(List<int[]> tickets, Flight flight) {
        List<int[]> validTickets = new ArrayList<>();
        if(flight == null) {
            return new ArrayList<>();
        }
        for (int[] ticket : tickets) {
            Seat seat= flight.getSeat(ticket[0], ticket[1]);
            if(seat == null || !seat.getIsAvailable()) {
                continue;
            }
            validTickets.add(ticket);
        }

        return validTickets;
    }

    @Transactional
    @Override
    public void purchaseTickets(List<int[]> tickets, Flight flight) {
        for (int[] ticket : tickets) {
            Seat seat = flight.getSeat(ticket[0], ticket[1]);
            if (seat != null && seat.getIsAvailable()) {
                seat.setIsAvailable(false);
            }
        }
        flightRepository.save(flight);
    }
    public List<String> getAllDestinationsThatContainString(String destination) {
        return flightRepository.findAllDestinationsThatContainString(destination);
    }
    public List<String> getAllDeparturesThatContainString(String departure) {
        return flightRepository.findAllDeparturesThatContainString(departure);
    }
}
