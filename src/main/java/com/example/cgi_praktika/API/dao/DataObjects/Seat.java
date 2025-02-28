package com.example.cgi_praktika.API.dao.DataObjects;

public class Seat {
    private final int rowID;
    private final int columnID;
    private final float footRoom;
    private final Seat rightSeat;
    private final Seat leftSeat;
    public Seat(int rowID, int columnID, float footRoom, Seat rightSeat, Seat leftSeat) {
        this.rowID = rowID;
        this.columnID = columnID;
        this.footRoom = footRoom;
        this.rightSeat = rightSeat;
        this.leftSeat = leftSeat;
    }

    public int getRowID() {
        return rowID;
    }

    public int getColumnID() {
        return columnID;
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
}
