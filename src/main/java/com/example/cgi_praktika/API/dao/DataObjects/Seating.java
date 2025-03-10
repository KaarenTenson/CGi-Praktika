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
}
