package com.moksh.skyreserve.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long airline_id;

    @Column(nullable = false)
    private String airline_name;

    @Column(nullable = false,unique = true)
    private String airline_code;

    private String airline_country;
    private String airline_headquarters;
    private String airline_email;
    @Column(length = 10)
    private String airline_contact;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "airline", cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Aircraft> aircraftList;

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
