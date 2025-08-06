package com.moksh.skyreserve.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aircraft_id;

    @Column(nullable = false)
    private String aircraft_model;

    @Column(nullable = false)
    private int capacity;

    private int economySeats;
    private int businessSeats;
    private int firstClassSeats;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "airline_id",nullable = false)
    private Airline airline;

    @OneToMany(mappedBy = "aircraft",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Seat> seatList=new ArrayList<>();

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flights;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}