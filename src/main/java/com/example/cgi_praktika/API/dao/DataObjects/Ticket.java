package com.example.cgi_praktika.API.dao.DataObjects;

import java.util.List;

public class Ticket {
    List<String> tickets;
    public Ticket(List<String> tickets) {
        this.tickets = tickets;
    }
    public List<String> getTickets() {
        return tickets;
    }
}
