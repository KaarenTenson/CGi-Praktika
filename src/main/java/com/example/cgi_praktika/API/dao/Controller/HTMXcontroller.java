package com.example.cgi_praktika.API.dao.Controller;
//import lombok.RequiredArgsConstructor;
import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Ticket;
import com.example.cgi_praktika.API.dao.Service.FlightService;
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
import java.util.stream.Collectors;

@Controller

public class HTMXcontroller {
    public int pageSize=30;
    private final FlightService flightService;
    @Autowired
    public HTMXcontroller(InMemoryFlightService flightService) {
        this.flightService = flightService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        List<Flight> flights=flightService.getAllFlights();
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

        List<Flight> flights = flightService.getAllFlights();
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


        if (!filteredUrl.isEmpty() && filteredUrl.charAt(filteredUrl.length() - 1) == '&') {
            filteredUrl.deleteCharAt(filteredUrl.length() - 1);
        }
        List<Flight> flights = flightService.getFilteredFlights(page, departure,
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
        model.addAttribute("filtered", filteredUrl.toString());
        model.addAttribute("sorting", sorting);
        model.addAttribute("asc", asc);
        return "flights";
    }


    @RequestMapping("/flights/seating/{id}")
    public String sortFlightTable(Model model, @PathVariable int id) {

        model.addAttribute("flight", flightService.getFlightById(id));

        return "seating";
    }
    @PostMapping("seating/purchase/{id}")
    public String purchaseFlight(Model model, @PathVariable int id, @RequestBody Map<String, List<String>> tickets) {
        if(tickets.get("tickets").isEmpty()){
            model.addAttribute("flight", flightService.getFlightById(id));
            return "seating";
        }
        List<int[]> ticketsList=tickets.get("tickets").stream().map( x -> new int[]{Integer.parseInt(x.split(" - ")[0]), Integer.parseInt(x.split(" - ")[1])}).toList();
        for (int[] ticket : ticketsList) {
            System.out.println(Arrays.toString(ticket));
        }
        Flight curFlight =flightService.getFlightById(id);
        float price= curFlight.getPrice();
        List<int[]> validTickets= flightService.validateTickets(ticketsList, curFlight);
        List<int[]> invalidTickets= new ArrayList<>(ticketsList);
        invalidTickets.removeAll(validTickets);
        model.addAttribute("validTickets", validTickets);
        model.addAttribute("invalidTickets", invalidTickets);
        model.addAttribute("totalPrice", price*validTickets.size());
        model.addAttribute("flightId", curFlight.getId());
        return "fragments/purchaseConfirmation";
    }
    @PostMapping("seating/purchase/confirm/{id}")
    public String confirmPurchaseFlight(Model model, @PathVariable int id, @RequestBody Map<String, List<int[]>> tickets) {
        boolean valid=true;
        List<int[]> ticketList=tickets.get("tickets");
        Flight curFlight =flightService.getFlightById(id);
        List<int[]> validTickets=flightService.validateTickets(ticketList,curFlight);
        if(validTickets.size()!=ticketList.size()){
            valid=false;
        }
        if(valid){
            flightService.purchaseTickets(ticketList,curFlight);
        }
        model.addAttribute("isValid", valid);
        return "fragments/purchaseResult";
    }


}
