package com.example.cgi_praktika.API.dao.Controller;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.Service.InMemoryFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FlightController {
    private InMemoryFlightService FlightService;
    @Autowired
    public FlightController(InMemoryFlightService flightService) {
        FlightService = flightService;
    }
    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return FlightService.getAllFlights();
    }
}
