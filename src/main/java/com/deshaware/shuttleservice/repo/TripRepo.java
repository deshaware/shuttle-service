package com.deshaware.shuttleservice.repo;

import java.time.Instant;
import java.util.List;

// import java.util.Optional;

import com.deshaware.shuttleservice.model.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long>{

    @Query(value = "SELECT * FROM Trip WHERE shuttle_id = :shuttle_id AND scheduled_on = :scheduled_on AND trip_status <> 'CANCELED'", nativeQuery = true)
    List<Trip> findDuplicateTrip(
        @Param("shuttle_id") String shuttle_id, 
        @Param("scheduled_on") Instant scheduled_on
    );

    @Query(value = "SELECT * FROM Trip WHERE trip_id = :trip_id", nativeQuery=true)
    Trip findTripById(
        @Param("trip_id") long trip_id
    );

    @Query(value = "SELECT * FROM Trip WHERE trip_id = :trip_id AND trip_status = 'INTIATED'", nativeQuery=true)
    Trip findActiveTripById(
        @Param("trip_id") long trip_id
    );
    
}
