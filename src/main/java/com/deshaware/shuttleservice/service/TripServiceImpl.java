package com.deshaware.shuttleservice.service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.*;
import com.deshaware.shuttleservice.repo.ShuttleRepo;
import com.deshaware.shuttleservice.repo.TripDetailRepo;
import com.deshaware.shuttleservice.repo.TripRepo;
import com.deshaware.shuttleservice.repo.UserRepo;
import com.deshaware.shuttleservice.response.Response;

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
    @Autowired
    TripDetailRepo tripDetailRepo;
    
    @Override
    public ResponseEntity<Response> addTrip(TripRequest tripRequest) {
        try {
            User driver = userRepo.findDriverByEmail(tripRequest.getDriver_id().toLowerCase());
            if (driver == null) {
                throw new Error("Driver does not exist");
            }
            Optional<Shuttle> shuttle = shuttleRepo.findById(tripRequest.getShuttle_id().toLowerCase());
            if (shuttle.isEmpty()) {
                throw new Error("Shuttle does not exist");
            }
            // logic to prevent duplicate trips
            List<Trip> trip = tripRepo.findDuplicateTrip(tripRequest.getShuttle_id(), tripRequest.getScheduled_on());
            if (!trip.isEmpty()) {
                return new ResponseEntity<Response>(new Response(){{
                    setMessage("The trip already exist with shuttle " + tripRequest.getShuttle_id() + " and scheduled on " + tripRequest.getScheduled_on());
                    setStatus("FAILED");

                }}, HttpStatus.BAD_REQUEST);
            }

            Trip new_trip = new Trip();
            new_trip.setDriver_id(driver); // different
            new_trip.setShuttle_id(shuttle.get()); // different way
            new_trip.setScheduled_on(tripRequest.getScheduled_on());
            new_trip.setStart_lang(tripRequest.getStart_lang());
            new_trip.setStart_lat(tripRequest.getStart_lat());
            new_trip.setModified(Instant.now());
            new_trip.setTrip_status(TripStatus.INTIATED);
            tripRepo.save(new_trip);
            logger.info("Trip Created");
            return new ResponseEntity<Response>(new Response(){{
                setData(new_trip);
                setStatus("SUCCESS");
            }} ,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while creating a trip " + e.getLocalizedMessage());
                setStatus("FAILED");
            }}, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> viewTrip(long trip_id) {
        try {
            logger.info("Request: TripService Implementation" + "method: viewTrip");
            Optional<Trip> trips = tripRepo.findById(trip_id);

            if (!trips.isPresent()) {
                throw new Error("Trip does not exist with trip_id: " + trip_id);
            }
            return new ResponseEntity<Response>(new Response(){{
                setData(trips);
                setMessage("Trip with trip_id: " + trip_id);
                setStatus("SUCCESS");
                setMessage("Trip fetched successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.warn("Request: TripService Implementation" + "method: viewTrip" + " error " + e.getLocalizedMessage());
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while creating a trip " + e.getLocalizedMessage());
                setStatus("FAILED");
            }}, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> viewAllTrip() {
        try {
            logger.info("Request: TripService Implementation" + "method: viewAllTrip");
            List<Trip> trips = tripRepo.findAll();
            return new ResponseEntity<Response>(new Response(){{
                setData(trips);
                setStatus("SUCCESS");
                setMessage("Trips fetched successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.warn("Request: TripService Implementation" + "method: viewAllTrip" + " error " + e.getLocalizedMessage());
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while creating a trip " + e.getLocalizedMessage());
                setStatus("FAILED");
            }}, HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Response> modifyTrip(TripRequest tripRequest, long trip_id){
        try {
            // validate trip data
            Trip trip = tripRepo.findTripById(trip_id);
            if (trip == null) {
                throw new Error("No trip found with trip_id: " + trip_id);
            }

            // if driver is updated
            if (tripRequest.getDriver_id() != null) {
                // get new driver
                User driver = userRepo.findDriverByEmail(tripRequest.getDriver_id().toLowerCase());
                // Optional<User> driver = userRepo.findByEmail(tripRequest.getDriver_id().toLowerCase());
                if (driver == null) {
                    throw new Error("Invalid Driver, Trip Cannot update!");
                }
                trip.setDriver_id(driver);
            }

            // shuttle
            if (tripRequest.getShuttle_id() != null) {
                Optional<Shuttle> shuttle = shuttleRepo.findById(tripRequest.getShuttle_id().toLowerCase());
                if (!shuttle.isPresent()) {
                    throw new Error("Invalid Shuttle, Trip Cannot update!");
                }
                trip.setShuttle_id(shuttle.get());
            }

            // or write another API for changing the status
            if(tripRequest.getTrip_status() != null){
                trip.setTrip_status(tripRequest.getTrip_status());
            }

            if (tripRequest.getStart_lang() != trip.getStart_lang() || tripRequest.getStart_lang() != trip.getStart_lat() ) {
                trip.setStart_lang(tripRequest.getStart_lang());
                trip.setStart_lat(tripRequest.getStart_lat());
            }

            if (tripRequest.getScheduled_on() != null && tripRequest.getScheduled_on() instanceof Instant) {
                trip.setScheduled_on(tripRequest.getScheduled_on());    
            }
            
            trip.setModified(Instant.now());
            tripRepo.save(trip);
            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(trip);
                setMessage("Trip Modified");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while modifying a trip " + e.getLocalizedMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<Response> deleteTrip(long trip_id){
        try {
            // validate trip data
            Optional<Trip> trips = tripRepo.findById(trip_id);
            if (trips.isEmpty()) {
                return new ResponseEntity<Response>(new Response(){{
                    setMessage("No trip found with trip_id: " + trip_id);
                    setStatus("FAILED");

                }}, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            tripRepo.deleteById(trip_id);
            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData("Trip "+ trip_id + " is deleted successully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Unable to delete trip");
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while deleting a trip: " + e.getLocalizedMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    


}
