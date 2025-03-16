package com.example.cgi_praktika.API.dao.DataGeneration;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import com.example.cgi_praktika.API.dao.DataObjects.Window;
import com.example.cgi_praktika.API.dao.Repositry.FlightRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class GenerateFlights {
    public static void generateFlights(FlightRepository flightRepository) {
        ArrayList<Flight> flights = new ArrayList<>();
        float[] pricePool= {30f, 20f, 23f, 30f, 18f, 40f};
        String[] destinationPool={"Tartu", "Tallinn", "Riia", "Helsingi", "New York", "Tokyo"};
        String[] departurePool={"NewYork", "Stockholm", "Vilnus", "Berlin", "Bejing", "London"};
        float[] flightTimePool={20f, 14f, 1000f, 200f, 40f, 30f, 30};
        LocalDateTime time= LocalDateTime.now();
        Random r=new Random();
        for (int i = 0; i < 500; i++) {
            float price = pricePool[r.nextInt(pricePool.length)];
            String destination = destinationPool[r.nextInt(destinationPool.length)];
            String departure = departurePool[r.nextInt(departurePool.length)];
            float flightTime = flightTimePool[r.nextInt(flightTimePool.length)];
            saveFlight(destination, departure, time,flightTime,price, flightRepository);
        }

    }
    public static void saveFlight( String destination, String departure, LocalDateTime time, float flightTime, float price, FlightRepository flightRepository) {
        ArrayList<Seat> seats=new ArrayList<>();
        ArrayList<Window> windows=new ArrayList<>();
        Flight flight= new Flight(destination, departure, time, price, flightTime, seats, windows);
        for (int i = 0; i < 30; i++) {
            if(i%2==0){
                Window leftWindow=new Window(0, i);
                leftWindow.setFlight(flight);
                Window rightWindow=new Window(1, i);
                rightWindow.setFlight(flight);
                windows.add(leftWindow);
                windows.add(rightWindow);
                ;}
            for (int j = 0; j < 7; j++) {
                if(j==3){
                    Seat newSeat= new Seat(i, j, 0.5f,"passThrough");
                    newSeat.setFlight(flight);
                    seats.add(newSeat);

                }else{
                    Seat newSeat= new Seat(i, j, 0.5f,"seat");
                    newSeat.setFlight(flight);
                    seats.add(newSeat);
                }


            }

        }

        flightRepository.save(flight);

    }

}
