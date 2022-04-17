package com.deshaware.shuttleservice.service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.*;
import com.deshaware.shuttleservice.repo.ShuttleRepo;
import com.deshaware.shuttleservice.repo.TripRepo;
import com.deshaware.shuttleservice.repo.UserRepo;
import com.deshaware.shuttleservice.response.ResponseFailure;
import com.deshaware.shuttleservice.response.ResponseSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Transactional
public class TripServiceImpl implements TripService{
    final static Logger logger = LogManager.getLogger(TripServiceImpl.class);

    @Autowired
    TripRepo tripRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ShuttleRepo shuttleRepo;
    @Override
    public ResponseEntity<?> addTrip(TripRequest tripRequest) {
        try {
            Optional<User> driver = userRepo.findByEmail(tripRequest.getDriver_id().toLowerCase());
            if (driver.equals(null)) {
                return new ResponseEntity<>("Driver does not exist", HttpStatus.BAD_REQUEST);
            }
            Optional<Shuttle> shuttle = shuttleRepo.findById(tripRequest.getShuttle_id().toLowerCase());
            if (shuttle.isEmpty()) {
                return new ResponseEntity<>("Shuttle does not exist", HttpStatus.BAD_REQUEST);
            }
            // logic to prevent duplicate trips
            List<Trip> trip = tripRepo.findDuplicateTrip(tripRequest.getShuttle_id(), tripRequest.getScheduled_on());
            if (!trip.isEmpty()) {
                return new ResponseEntity<ResponseFailure>(new ResponseFailure(){{
                    setReason("The trip already exist with shuttle " + tripRequest.getShuttle_id() + " and scheduled on " + tripRequest.getScheduled_on());
                    setHttpStatus(HttpStatus.BAD_REQUEST);
                    setProcessed_on(Instant.now());
                }}, HttpStatus.BAD_REQUEST);
            }

            Trip new_trip = new Trip();
            new_trip.setDriver_id(driver.get()); // different
            new_trip.setShuttle_id(shuttle.get()); // different way
            new_trip.setScheduled_on(tripRequest.getScheduled_on());
            new_trip.setModified(Instant.now());
            new_trip.setTrip_status(TripStatus.INTIATED);
            tripRepo.save(new_trip);
            logger.info("Trip Created");
            return new ResponseEntity<ResponseSuccess>(new ResponseSuccess(){{
                setData(new_trip);
                setHttpStatus(HttpStatus.CREATED);
                setProcessed_on(Instant.now());
            }} ,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<ResponseFailure>(new ResponseFailure(){{
                setReason("Error while creating a trip " + e.getLocalizedMessage());
                setHttpStatus(HttpStatus.BAD_REQUEST);
                setProcessed_on(Instant.now());
            }}, HttpStatus.BAD_REQUEST);
        }
    }
    
}
