package com.deshaware.shuttleservice.service;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.ObjDoubleConsumer;

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
public class TripDetailServiceImpl implements TripDetailService{
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
    public ResponseEntity<Response> enrollTrip(long trip_id, String email, TripDetail tripDetail){
        try {
            // validate trip data
            Trip trip = tripRepo.findActiveTripById(trip_id);
            if (trip == null) {
                throw new Error("No active trip found with trip_id: " + trip_id);
            }

            User user = userRepo.findActiveUser(email);
            if (user == null || !user.isEnabled() || user.getRole() != Role.USER) {
                throw new Error("Unable to enroll user : " + email);
            }

            // check trip user
            TripDetail checkTripDetail = tripDetailRepo.findByExistingUser(trip_id, email);

            // check if user exist in the set
            if (checkTripDetail != null && trip.getTrip_users().contains(checkTripDetail.getTrip_detail_id())) {
                throw new Error("User: " + email +" has already registered for the trip : " + trip_id);
            }

            // check if capacity allows
            if (!trip.canEnroll()) {
                throw new Error("Shuttle is full" + trip_id);
            }

            // check all exist
            if (tripDetail.getEnd_lat() == 0 || tripDetail.getEnd_long() == 0
            || tripDetail.getStart_long() == 0 || tripDetail.getStart_lat() == 0
            ){
                throw new Error("Problems with Start and End coordinates ");
            }

            TripDetail newTripDetail = new TripDetail();
            newTripDetail.setEnd_lat( tripDetail.getEnd_lat());
            newTripDetail.setEnd_long( tripDetail.getEnd_long());
            newTripDetail.setStart_long( tripDetail.getStart_long());
            newTripDetail.setStart_lat( tripDetail.getStart_lat());
            newTripDetail.setUser(user);
            newTripDetail.setTrip(trip);

            newTripDetail.setModified(Instant.now());
            newTripDetail.setStatus(TripDetailStatus.REGISTERED);
            TripDetail freshTripDetail = tripDetailRepo.save(newTripDetail);
            System.out.println("fresh" + freshTripDetail);
            trip.enrollUser(freshTripDetail.getTrip_detail_id());
            // trip.enrollUser(freshTripDetail);
            tripRepo.save(trip);

            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(freshTripDetail);
                setMessage("Trip Modified: User Enrolled Successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.catching(e);
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while enrolling user to a trip " + e.getMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> unenrollTrip(long trip_id, String email){
        try{
            logger.info("Unenroll the trip: " + trip_id + " by " + email);
            // validate trip data
            Trip trip = tripRepo.findActiveTripById(trip_id);
            if (trip == null) {
                throw new Error("No active trip found with trip_id: " + trip_id);
            }

            User user = userRepo.findActiveUser(email);
            if (user == null || !user.isEnabled() || user.getRole() != Role.USER) {
                throw new Error("Invalid user or user not active : " + email);
            }

            // check trip user
            TripDetail checkTripDetail = tripDetailRepo.findByExistingUser(trip_id, email);

            // check if user exist in the set
            if (checkTripDetail == null) {
                throw new Error("User "+ email +" is not registerd for the trip:" + trip_id);
            }

            // check if the trip is started or not
            if (trip.getTrip_status() != TripStatus.INTIATED) {
                throw new Error("Cannot unenroll, trip may have already started or over. Trip: " + trip_id);
            }

            // remove from the trip hashset
            trip.getTrip_users().remove(checkTripDetail.getTrip_detail_id());

            // change TripDetail status to cancel
            checkTripDetail.setStatus(TripDetailStatus.CANCELED);

            tripRepo.save(trip);
            tripDetailRepo.save(checkTripDetail);
            
            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(checkTripDetail);
                setMessage("Trip Modified: User Unenrolled successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error in unrolling the trip", e);
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while unenrolling user to a trip " + e.getMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * flow
     * 1. check trip id
     * 2. get all the trip_details_id
     * 3. call distance api and create a list
     * 4. 
     */
    @Override
    public ResponseEntity<Response> startTrip(long trip_id) {// accessible to drivers
        try {
            logger.info("Start trip: " + trip_id);
            Trip trip = tripRepo.findActiveTripById(trip_id);
            if (trip == null) {
                throw new Error("No active trip found with trip_id: " + trip_id);
            }

            // no need, we can just take users from the trip and start them off
            // as we are updating the trip
            HashSet<Long> trip_users = trip.getTrip_users();
            System.out.println(trip_users);

            tripDetailRepo.setStatusForTripDetails(trip_users, TripDetailStatus.ON_THE_WAY.toString());
            
            // start trip
            trip.setTrip_status(TripStatus.IN_PROGRESS);

            // get distance api and return
            tripRepo.save(trip);

            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(trip);
                setMessage("Trip Started Successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error while starting the trip" + e.getMessage());
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while starting a trip " + e.getMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Response> endTrip(long trip_id){
        try {
            logger.info("endTrip trip: " + trip_id);
            Trip trip = tripRepo.findTripByStatusAndId(trip_id, TripStatus.IN_PROGRESS.toString());
            if (trip == null) {
                throw new Error("No active trip found with trip_id: " + trip_id);
            }

            // no need, we can just take users from the trip and start them off
            // as we are updating the trip
            HashSet<Long> trip_users = trip.getTrip_users();
            System.out.println(trip_users);

            tripDetailRepo.setStatusForTripDetails(trip_users, TripDetailStatus.COMPLETED.toString());
            
            // start trip
            trip.setTrip_status(TripStatus.COMPLETED);

            // get distance api and return
            tripRepo.save(trip);

            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(trip);
                setMessage("Trip Ended Successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error while ending the trip" + e.getMessage());
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while ending a trip " + e.getMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 


    public ResponseEntity<Response> viewAllTripDetails(){
        try {
            logger.info("View all trips");
            List<TripDetail> tripDetails = tripDetailRepo.findAll();
        
            return new ResponseEntity<Response>(new Response(){{
                setStatus("SUCCESS");
                setData(tripDetails);
                setMessage("Trip Details Fetched Successfully");
            }}, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error while fetching all trip details " + e.getMessage());
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while ending a trip " + e.getMessage());
                setStatus("FAILED");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
