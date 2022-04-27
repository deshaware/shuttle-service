package com.deshaware.shuttleservice.model;

import java.time.Instant;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

import org.springframework.lang.Nullable;

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

    @Nullable
    private double curr_lon;
    @Nullable
    private double curr_lat;

    @Enumerated(EnumType.STRING)
    private TripStatus trip_status;
    
    // driver
    @ManyToOne
    @JoinColumn(name = "email")
    private User driver_id;

    @ManyToOne
    @JoinColumn(name="shuttle_id")
    private Shuttle shuttle_id;

    // will be limited to the capacity
    private HashSet<Long> trip_users = new HashSet<Long>();

    private Instant modified;

    public void enrollUser(Long trip_user){
        this.trip_users.add(trip_user);
    }

    public void unenrollUser(Long user){
        this.trip_users.remove(user);
    }

    public boolean canEnroll(){
        return this.trip_users.size() <= this.shuttle_id.getCapacity();
    }
    

}
