package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.Trip;
import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.response.Response;
import com.deshaware.shuttleservice.service.TripDetailService;
import com.deshaware.shuttleservice.service.TripService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tripdetail")
@AllArgsConstructor
public class TripDetailController {

    private final TripDetailService tripDetailService;

    @PutMapping("/enrollTrip")
    public ResponseEntity<Response> entrollTrip(
        @RequestParam long trip_id, String email,
        @RequestBody TripDetail tripDetail
    ) {
        return tripDetailService.enrollTrip(trip_id, email, tripDetail);
    }
    
    @PutMapping("/unenrollTrip")
    public ResponseEntity<Response> unentrollTrip(
        @RequestParam long trip_id, String email
    ) {
        return tripDetailService.unenrollTrip(trip_id, email);
    }

    @PostMapping("/startTrip/{trip_id}")
    public ResponseEntity<Response> startTrip(
        @PathVariable long trip_id
    ) {
        // once started, cannot modify the trip
        return tripDetailService.startTrip(trip_id);
    }

    @PostMapping("/endTrip/{trip_id}")
    public ResponseEntity<Response> endTrip(
        @PathVariable long trip_id
    ) {
        // once started, cannot modify the trip
        return tripDetailService.endTrip(trip_id);
    }

    @GetMapping("/viewAllTripDetails")
    public ResponseEntity<Response> viewAllTripDetails(
    ) {
        return tripDetailService.viewAllTripDetails();
    }
    
}
