package com.example.cgi_praktika.API.dao.Controller;
//import lombok.RequiredArgsConstructor;
import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.Service.InMemoryFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller

//@RequiredArgsConstructor
public class HTMXcontroller {
    private InMemoryFlightService FlightService;
    @Autowired
    public HTMXcontroller(InMemoryFlightService flightService) {
        FlightService = flightService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("destinations", FlightService.getDestinations());
        return "main";
    }
    @RequestMapping("/flights/{destination}/page={page}")
    public String flight(Model model, @PathVariable String destination, @PathVariable int page) {

        System.out.println("flight request:" + destination);
        ArrayList<Flight> flights = FlightService.getAllFlightsByDestination(destination);
        int pageCount = flights.size()/30 + Math.min(flights.size()%30, 1);
        model.addAttribute("page", page);
        model.addAttribute("pages", pageCount);
        model.addAttribute("flights", flights.subList((page-1)*30, Math.min(30*page, flights.size())));

        return "flights";
    }
    @RequestMapping("/flights/{destination}/{column}")
    public String sortFlightTable(Model model, @PathVariable String destination,@PathVariable String column) {

        System.out.println("got sort request:" + destination);
        model.addAttribute("flights", FlightService.getSortedFlights(destination, column, false));

        return "fragments/flightsTableData";
    }
    @RequestMapping("/flights/seating/{id}")
    public String sortFlightTable(Model model, @PathVariable int id) {

        model.addAttribute("flight", FlightService.getFlightById(id));

        return "seating";
    }

}
