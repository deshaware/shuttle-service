package com.deshaware.shuttleservice.service;

import java.lang.StackWalker.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.*;
import com.deshaware.shuttleservice.repo.ShuttleRepo;
import com.deshaware.shuttleservice.repo.TripRepo;
import com.deshaware.shuttleservice.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TripServiceImpl implements TripService{


    @Autowired
    TripRepo tripRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ShuttleRepo shuttleRepo;

    @Override
    public ResponseEntity<?> addTrip(TripRequest tripRequest) {
        List<Trip> trip = tripRepo.findAll();
        // if (!trip.isEmpty()) {
        //     return new ResponseEntity<>("Trip already exist", HttpStatus.BAD_REQUEST);
        // }
        Optional<User> driver = userRepo.findByEmail(tripRequest.getDriver_id().toLowerCase());
        if (driver.equals(null)) {
            return new ResponseEntity<>("Driver does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Shuttle> shuttle = shuttleRepo.findById(tripRequest.getShuttle_id().toLowerCase());
        if (shuttle.isEmpty()) {
            return new ResponseEntity<>("Shuttle does not exist", HttpStatus.BAD_REQUEST);
        }
        Trip new_trip = new Trip();
        new_trip.setDriver_id(driver.get()); // different
        new_trip.setShuttle_id(shuttle.get()); // different way
        new_trip.setScheduled_on(tripRequest.getScheduled_on());
        new_trip.setModified(Instant.now());
        new_trip.setTrip_status(TripStatus.INTIATED);
        tripRepo.save(new_trip);
        return new ResponseEntity<>(new_trip,HttpStatus.CREATED);
    }
    
}
