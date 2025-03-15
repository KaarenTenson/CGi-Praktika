package com.example.cgi_praktika.API.dao.DataObjects;

import java.util.Random;

public class Seat {
    private final int row;
    private final int column;
    private final float footRoom;
    private final String seatType;
    private boolean isAvailable;
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
}
