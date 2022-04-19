package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface TripDetailService {
    public ResponseEntity<Response> enrollTrip(long trip_id, String email, TripDetail tripDetail);

    public ResponseEntity<Response> unenrollTrip(long trip_id, String email);

    // public ResponseEntity<Response> startTrip(long trip_id);

    // public ResponseEntity<Response> endTrip(long trip_id);

    // public void createTrip(Trip trip);
    
}
