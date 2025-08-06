package com.moksh.skyreserve.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservation_id;

    @Column(nullable = false,unique = true)
    private String pnr;

    @Column(nullable = false)
    private LocalDateTime booking_date;

    @Column(nullable = false)
    private String reservation_status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime cancellationDate;
    private String cancellationReason;

/*
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private BigDecimal totalAmount;

    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
    private Integer passengerAge;
    private String passengerGender;
*/

    @ManyToOne
    @JoinColumn(name = "user_id_fk",referencedColumnName = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id_fk",referencedColumnName = "schedule_id",nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "seat_id_fk",referencedColumnName = "seat_id",nullable = false)
    private Seat seat;

    @PrePersist
    protected void onCreate() {
        createdAt=LocalDateTime.now();
        //updatedAt=LocalDateTime.now();
        booking_date=LocalDateTime.now();
        if (reservation_status==null) reservation_status="CONFIRMED";
        if (pnr==null) {
            pnr=generatePNR();
        }
    }
    private String generatePNR() {
        return "PNR"+System.currentTimeMillis()%1000000;
    }
}
