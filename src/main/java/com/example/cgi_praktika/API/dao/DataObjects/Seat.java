package com.example.cgi_praktika.API.dao.DataObjects;

import java.util.Random;

public class Seat {
    private final int row;
    private final int column;
    private final float footRoom;
    private final Seat rightSeat;
    private final Seat leftSeat;
    private final String seatType;
    private boolean isAvailable;
    public Seat(int rowID, int column, float footRoom, Seat rightSeat, Seat leftSeat, String seatType) {
        this.row = rowID;
        this.column = column;
        this.footRoom = footRoom;
        this.rightSeat = rightSeat;
        this.leftSeat = leftSeat;
        this.seatType = seatType;
        this.isAvailable = new Random().nextBoolean();
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

    public Seat getRightSeat() {
        return rightSeat;
    }

    public Seat getLeftSeat() {
        return leftSeat;
    }
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }
}
