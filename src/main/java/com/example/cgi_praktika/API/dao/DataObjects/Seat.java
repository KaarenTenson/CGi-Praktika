package com.example.cgi_praktika.API.dao.DataObjects;

import jakarta.persistence.*;

import java.util.Random;
@Entity
@Table(name = "SEAT")
public class Seat {
    @Id
    private Long id;
    @Column(nullable = false)
    private int row;
    @Column(nullable = false)
    private int column;
    @Column
    private float footRoom;
    @Column(nullable = false)
    private String seatType;
    @Column(nullable = false)
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


    public Seat(int rowID, int column, float footRoom, String seatType) {
        this.row = rowID;
        this.column = column;
        this.footRoom = footRoom;
        this.seatType = seatType;
        if(seatType.equals("seat")){
        this.isAvailable = new Random().nextBoolean();}else {
            this.isAvailable = false;
        }
    }

    public Seat() {

    }

    public String getSeatType() {
        return seatType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public float getFootRoom() {
        return footRoom;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
