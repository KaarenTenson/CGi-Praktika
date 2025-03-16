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
    private  Long id;
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
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Window> windows;


    public  Flight(String destination, String departure, LocalDateTime departureTime, float flightTime, float price, ArrayList<Seat> seats, ArrayList<Window> windows) {
        this.destination = destination;
        this.departure = departure;
        this.departureTime = departureTime;
        this.flightTime = flightTime;
        this.price = price;
        this.seats = seats;
        this.windows = windows;

    }

    public Flight() {
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    public List<Window> getWindows() {
        return windows;
    }
    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }
    public Seat[][] getSeatMatrix() {
        int rowSize=0;
        int columnSize=0;
        List<Seat> curSeats=this.getSeats();
        for (Seat seat : curSeats) {
            if(seat.getRow()>rowSize){
                rowSize=seat.getRow();
            }
            if(seat.getColumn()>columnSize){
                columnSize=seat.getColumn();
            }
        }
        Seat[][] seatMatrix = new Seat[rowSize+1][columnSize+1];
        for(Seat seat : curSeats){
            seatMatrix[seat.getRow()][seat.getColumn()] = seat;
        }
        return seatMatrix;
    }
    public int[][] getWindowMatrix(int rowSize) {

        int[][] windowMatrix = new int[rowSize+1][2];
        for(Window window : windows){
            windowMatrix[window.getRow()][window.getColumn()]=1;
        }
        return windowMatrix;
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
    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
    public Seat  getSeat(int RowId, int ColumnId){
       for (Seat seat : seats) {
           if(seat.getRow()==RowId && seat.getColumn()==ColumnId){
               return seat;
           }
       }
       return null;
    }
}
