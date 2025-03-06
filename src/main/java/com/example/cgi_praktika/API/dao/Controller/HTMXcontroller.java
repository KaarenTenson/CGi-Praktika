package com.example.cgi_praktika.API.dao.Controller;
//import lombok.RequiredArgsConstructor;
import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Ticket;
import com.example.cgi_praktika.API.dao.Service.InMemoryFlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller

//@RequiredArgsConstructor
public class HTMXcontroller {
    public int pageSize=30;
    private InMemoryFlightService FlightService;
    @Autowired
    public HTMXcontroller(InMemoryFlightService flightService) {
        FlightService = flightService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        List<Flight> flights=FlightService.getAllFlights();
        int pageCount = flights.size()/pageSize + Math.min(flights.size()%pageSize, 1);
        model.addAttribute("page", 1);
        model.addAttribute("pages", pageCount);
        model.addAttribute("flights", flights.subList(0, Math.min(pageSize, flights.size())));
        model.addAttribute("filtered", "/page="+1);
        return "flights";
    }
    @RequestMapping("/page/{page}/filtered")
    public String flightFiltered(
            Model model,
            @PathVariable int page,
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        StringBuilder filteredUrl = new StringBuilder("/filtered?");

        if (departure != null) filteredUrl.append("departure=").append(departure).append("&");
        if (destination != null) filteredUrl.append("destination=").append(destination).append("&");
        if (minPrice != null) filteredUrl.append("minPrice=").append(minPrice).append("&");
        if (maxPrice != null) filteredUrl.append("maxPrice=").append(maxPrice).append("&");

        // Remove the last "&" if there are parameters
        if (filteredUrl.charAt(filteredUrl.length() - 1) == '&') {
            filteredUrl.deleteCharAt(filteredUrl.length() - 1);
        }
        System.out.println(filteredUrl.toString());
        // Add attributes to the model
        ArrayList<Flight> flights = FlightService.getFilteredFlights(page, departure, destination, minPrice, maxPrice);
        int pageCount = flights.size() / pageSize + Math.min(flights.size() % pageSize, 1);

        model.addAttribute("page", page);
        model.addAttribute("pages", pageCount);
        model.addAttribute("departure", departure == null ? "" : departure);
        model.addAttribute("destination", destination == null ? "" : destination);
        model.addAttribute("minPrice",  minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("flights", flights.subList((page - 1) * pageSize, Math.min(pageSize * page, flights.size())));
        model.addAttribute("filtered", filteredUrl.toString()); // Pass the filtered URL

        return "flights";
    }

    @RequestMapping("/page={page}")
    public String flight(Model model, @PathVariable int page) {

        List<Flight> flights = FlightService.getAllFlights();
        int pageCount = flights.size()/pageSize + Math.min(flights.size()%pageSize, 1);
        model.addAttribute("page", page);
        model.addAttribute("pages", pageCount);
        model.addAttribute("flights", flights.subList((page-1)*pageSize, Math.min(pageSize*page, flights.size())));

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
    @PostMapping("seating/purchase/{id}")
    public String purchaseFlight(Model model, @PathVariable int id, @RequestParam Map<String, List<String>> tickets) {
        System.out.println(tickets.get("tickets"));
        //System.out.println(payload.get("tickets"));
        //model.addAttribute("tickets", payload.get("tickets"));
        return "fragments/purchaseConfirmation";
    }


}
