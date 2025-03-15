package com.example.cgi_praktika.API.dao.DataObjects;

public class Seating {
    private final Seat[][] PlaneStructure;
    private final int[][] windows;
    public Seating(Seat[][] planeStructure, int[][] windows) {
        PlaneStructure = planeStructure;
        this.windows = windows;
    }
    public Seat[][] getPlaneStructure() {
        return PlaneStructure;
    }
    public int[][] getWindows() {
        return windows;
    }
    public Seat getSeat(int row, int column) {
        if(row>=0 && row < PlaneStructure.length && column >= 0 && column < PlaneStructure[row].length) {
            return PlaneStructure[row][column];
        }
        return null;
    }
}
