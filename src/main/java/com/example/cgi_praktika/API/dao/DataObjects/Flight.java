package com.example.cgi_praktika.API.dao.DataObjects;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.persistence.*;
@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private  String destination;
    @Column(nullable = false)
    private  String departure;
    @Column(nullable = false)
    private  LocalDateTime departureTime;
    //in hours
    @Column(nullable = false)
    private float flightTime;
    @Column(nullable = false)
    private float price;
    @Transient
    private Seating seating;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seatsDb;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Window> window;


    public  Flight(String destination, String departure, LocalDateTime departureTime, float flightTime, float price, Seating seats) {
        this.destination = destination;
        this.departure = departure;
        this.departureTime = departureTime;
        this.flightTime = flightTime;
        this.price = price;
        this.seating = seats;
        Random rand = new Random();
        this.id = rand.nextInt(1000000);

    }

    public Flight() {
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination=destination;
    }
    public String getDeparture() {
        return departure;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public float getFlightTime() {
        return flightTime;
    }
    public Seating getSeats() {
        return seating;
    }
    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
    public Seat  getSeatById(int RowId, int ColumnId){
        return this.seating.getPlaneStructure()[RowId][ColumnId];
    }
}
