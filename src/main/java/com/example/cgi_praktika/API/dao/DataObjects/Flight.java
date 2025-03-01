package com.example.cgi_praktika.API.dao.DataObjects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Flight {
    private final int id;
    private final String destination;
    private final Timestamp departureTime;
    //in hours
    private final float flightTime;
    private final float price;
    private final ArrayList<Seat> seats;
    public  Flight(String destination, Timestamp departureTime, float flightTime, float price, ArrayList<Seat> seats) {
        this.destination = destination;
        this.departureTime = departureTime;
        this.flightTime = flightTime;
        this.price = price;
        this.seats = seats;
        Random rand = new Random();
        this.id = rand.nextInt(1000000);

    }

    public String getDestination() {
        return destination;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public float getFlightTime() {
        return flightTime;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
    public Seat  getSeatById(int RowId, int ColumnId){
        for (Seat seat : seats) {
            if(seat.getRowID() == RowId && seat.getColumnID() == ColumnId){
                return seat;
            }
        }
        //muuda exceptioniks
        return null;
    }
}
