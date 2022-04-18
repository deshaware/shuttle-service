package com.deshaware.shuttleservice.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trip_id;
    
    private Instant scheduled_on;
    private double start_lang;
    private double start_lat;

    @Enumerated(EnumType.STRING)
    private TripStatus trip_status;
    
    // driver
    @ManyToOne
    @JoinColumn(name = "email")
    private User driver_id;

    @ManyToOne
    @JoinColumn(name="shuttle_id")
    private Shuttle shuttle_id;

    private Instant modified;

}
