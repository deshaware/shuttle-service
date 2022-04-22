package com.deshaware.shuttleservice.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

// Used for logging purpose, activity purpose
public class TripDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trip_detail_id;

    // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName="email")
    // private HashSet<User> users = new HashSet<User>();
    private User user;

    // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName="trip_id")
    private Trip trip;

    private double start_long;
    private double start_lat;
    private double end_long;
    private double end_lat;

    private Instant est_pickup;
    private int waitlist;
    
    @Enumerated(EnumType.STRING)
    private TripDetailStatus status;
    
    private Instant modified;

}
