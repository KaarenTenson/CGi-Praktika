package com.example.cgi_praktika.API.dao.DataObjects;

public class Seating {
    private final Seat[][] PlaneStructure;

    public Seating(Seat[][] planeStructure) {
        PlaneStructure = planeStructure;
    }
    public Seat[][] getPlaneStructure() {
        return PlaneStructure;
    }
}
