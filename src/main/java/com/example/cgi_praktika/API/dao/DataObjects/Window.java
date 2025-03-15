package com.example.cgi_praktika.API.dao.DataObjects;

import jakarta.persistence.*;

@Entity
@Table(name = "WINDOW")
public class Window {
    @Id
    private Long id;
    @Column(nullable = false)
    private int row;
    @Column(nullable = false)
    private int column;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Window(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Window() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getRow() {
        return row;
    }
   public int getColumn() {
        return column;
   }
}
