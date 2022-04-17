package com.deshaware.shuttleservice.repo;

// import java.util.Optional;

import com.deshaware.shuttleservice.model.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepo extends JpaRepository<Trip, String>{
    
    
}
