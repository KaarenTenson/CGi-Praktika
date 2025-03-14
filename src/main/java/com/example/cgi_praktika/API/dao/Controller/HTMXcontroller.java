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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Controller

//@RequiredArgsConstructor
public class HTMXcontroller {
    public int pageSize=30;
    public  int visiblePages = 5;
    private InMemoryFlightService FlightService;
    @Autowired
    public HTMXcontroller(InMemoryFlightService flightService) {
        FlightService = flightService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        List<Flight> flights=FlightService.getAllFlights();
        int pageCount = flights.size()/pageSize + Math.min(flights.size()%pageSize, 1);
        model.addAttribute("previousPage", -1);
        model.addAttribute("page", 1);
        model.addAttribute("nextPage", 2);
        model.addAttribute("pages", pageCount);
        model.addAttribute("flights", flights.subList(0, Math.min(pageSize, flights.size())));
        model.addAttribute("filtered", "");
        return "flights";
    }
    @RequestMapping("/page/{page}")
    public String flight(Model model, @PathVariable int page) {

        List<Flight> flights = FlightService.getAllFlights();
        int pageCount = flights.size()/pageSize + Math.min(flights.size()%pageSize, 1);
        model.addAttribute("previousPage", page-1);
        model.addAttribute("page", page);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("pages", pageCount);
        model.addAttribute("flights", flights.subList((page-1)*pageSize, Math.min(pageSize*page, flights.size())));
        model.addAttribute("filtered", "");

        return "flights";
    }
    @RequestMapping("/page/{page}/filtered")
    public String flightFiltered(
            Model model,
            @PathVariable int page,
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) LocalDateTime minTime,
            @RequestParam(required = false) LocalDateTime maxTime,
            @RequestParam(required = false) String sorting,
            @RequestParam(defaultValue = "true") boolean asc
            ) {
        StringBuilder filteredUrl = new StringBuilder();
        if (departure != null) filteredUrl.append("departure=").append(departure).append("&");
        if (destination != null) filteredUrl.append("destination=").append(destination).append("&");
        if (minPrice != null) filteredUrl.append("minPrice=").append(minPrice).append("&");
        if (maxPrice != null) filteredUrl.append("maxPrice=").append(maxPrice).append("&");
        if (minTime != null) filteredUrl.append("minTime=").append(minTime).append("&");
        if (maxTime != null) filteredUrl.append("maxTime=").append(maxTime).append("&");


        // Remove the last "&" if there are parameters
        if (!filteredUrl.isEmpty() && filteredUrl.charAt(filteredUrl.length() - 1) == '&') {
            filteredUrl.deleteCharAt(filteredUrl.length() - 1);
        }
        List<Flight> flights = FlightService.getFilteredFlights(page, departure,
                destination, minPrice, maxPrice,
                minTime, maxTime,
                sorting, asc
        );
        int pageCount = flights.size() / pageSize + Math.min(flights.size() % pageSize, 1);
        model.addAttribute("previousPage", page-1);
        model.addAttribute("page", page);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("pages", pageCount);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("minPrice",  minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minTime", minTime);
        model.addAttribute("maxTime", maxTime);
        model.addAttribute("flights", flights.subList((page - 1) * pageSize, Math.min(pageSize * page, flights.size())));
        model.addAttribute("filtered", filteredUrl.toString()); // Pass the filtered URL
        model.addAttribute("sorting", sorting);
        model.addAttribute("asc", asc);
        return "flights";
    }


    @RequestMapping("/flights/seating/{id}")
    public String sortFlightTable(Model model, @PathVariable int id) {

        model.addAttribute("flight", FlightService.getFlightById(id));

        return "seating";
    }
    @PostMapping("seating/purchase/{id}")
    public String purchaseFlight(Model model, @PathVariable int id, @RequestBody Map<String, List<String>> tickets) {
        model.addAttribute("tickets", tickets.get("tickets"));
        return "fragments/purchaseConfirmation";
    }


}
