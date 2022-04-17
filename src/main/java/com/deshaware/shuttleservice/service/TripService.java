package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.Trip;

import org.springframework.http.ResponseEntity;

public interface TripService {
    public ResponseEntity<?> addTrip(TripRequest tripRequest);

    // public ResponseEntity<?> viewAllTrip();

    // public ResponseEntity<?> modifyTrip(TripRequest tripRequest);

    // public ResponseEntity<?> deleteTrip(String shuttle_id);

    // public void createTrip(Trip trip);

}
