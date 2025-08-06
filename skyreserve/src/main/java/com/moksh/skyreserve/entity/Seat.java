package com.moksh.skyreserve.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seat_id;

    @Column(nullable = false)
    private String seat_number;

    @Column(nullable = false)
    private String seat_class;

    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne()
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @OneToMany(mappedBy = "seat")
    private List<Reservation> reservationList;
}
