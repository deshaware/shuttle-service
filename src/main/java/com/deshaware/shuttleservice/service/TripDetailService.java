package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface TripDetailService {
    public ResponseEntity<Response> enrollTrip(long trip_id, String email, TripDetail tripDetail);

    public ResponseEntity<Response> unenrollTrip(long trip_id, String email);

    // public ResponseEntity<Response> getTripHistoryByUser(String email);

    public ResponseEntity<Response> getTripDetail(long trip_detail_id);

    public ResponseEntity<Response> startTrip(long trip_id); // accessible to drivers

    public ResponseEntity<Response> endTrip(long trip_id); // accessible to drivers

    public ResponseEntity<Response> viewAllTripDetails(); // accessible to drivers

    // public ResponseEntity<Response> dropOff(long trip_id, long trip_detail_id); // accessible to drivers
    
}
