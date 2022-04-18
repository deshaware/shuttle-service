package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.TripDetail;
// import com.deshaware.shuttleservice.model.Trip;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface TripService {
    public ResponseEntity<?> addTrip(TripRequest tripRequest);

    public ResponseEntity<Response> viewAllTrip();

    public ResponseEntity<Response> modifyTrip(TripRequest tripRequest, long trip_id);

    public ResponseEntity<Response> deleteTrip(long trip_id);

    public ResponseEntity<Response> enrollTrip(long trip_id, String email, TripDetail tripDetail);

    // public ResponseEntity<Response> unenrollTrip(long trip_id, String email);

    // public ResponseEntity<Response> startTrip(long trip_id);

    // public ResponseEntity<Response> endTrip(long trip_id);

    // public void createTrip(Trip trip);

}
