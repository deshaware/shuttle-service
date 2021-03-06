package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface TripService {
    public ResponseEntity<?> addTrip(TripRequest tripRequest);

    public ResponseEntity<Response> viewAllTrip();

    public ResponseEntity<Response> modifyTrip(TripRequest tripRequest, long trip_id);

    public ResponseEntity<Response> deleteTrip(long trip_id);

    public ResponseEntity<?> viewTrip(long trip_id);

}
