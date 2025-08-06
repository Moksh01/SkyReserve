package com.moksh.skyreserve.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long airport_id;

    @Column(nullable = false, unique = true, length = 3)
    private String airport_code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "departureAirport")
    private List<Schedule> departureSchedules;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Schedule> arrivalSchedules;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
