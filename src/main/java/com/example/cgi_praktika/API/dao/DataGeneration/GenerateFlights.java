package com.example.cgi_praktika.API.dao.DataGeneration;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import com.example.cgi_praktika.API.dao.DataObjects.Seating;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Random;

public class GenerateFlights {
    public static ArrayList<Flight> generateFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        float[] pricePool= {30f, 20f, 23f, 30f, 18f, 40f};
        String[] destinationPool={"Tartu", "Tallinn", "Riia", "Helsingi", "New York", "Tokyo"};
        String[] departurePool={"NewYork", "Stockholm", "Vilnus", "Berlin", "Bejing", "London"};
        float[] flightTimePool={20f, 14f, 1000f, 200f, 40f, 30f, 30};
        LocalDateTime time= LocalDateTime.now();
        Random r=new Random();
        for (int i = 0; i < 2000; i++) {
            float price = pricePool[r.nextInt(pricePool.length)];
            String destination = destinationPool[r.nextInt(destinationPool.length)];
            String departure = departurePool[r.nextInt(departurePool.length)];
            float flightTime = flightTimePool[r.nextInt(flightTimePool.length)];
            flights.add(new Flight(destination,departure,time, flightTime, price, generateSeating()));
        }
        return flights;
    }
    public static Seating generateSeating() {
        Seat[][] seats=new Seat[30][7];
        int[][] windows=new int[30][2];
        for (int i = 0; i < 30; i++) {
            if(i%2==0){
                windows[i][0]=1;
                windows[i][1]=1;}
            for (int j = 0; j < 7; j++) {
                if(j==3){
                    seats[i][j]=new Seat(i, j, 0.5f,"passThrough");
                }else{
                seats[i][j]=new Seat(i, j, 0.5f, "seat");}


            }

        }
        return new Seating(seats, windows);
    }

}
