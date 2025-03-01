package com.example.cgi_praktika.API.dao.Controller;
//import lombok.RequiredArgsConstructor;
import com.example.cgi_praktika.API.dao.Service.InMemoryFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/flights/{destination}")
    public String flight(Model model, @PathVariable String destination) {

        System.out.println("flight request:" + destination);
        model.addAttribute("flights", FlightService.getAllFlightsByDestination(destination));
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
