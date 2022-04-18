package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.Trip;
import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.response.Response;
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
@RequestMapping("/api/trip")
@AllArgsConstructor
public class TripController {

    private final TripService tripService;
    
    @PostMapping("/addTrip")
    public ResponseEntity<?> addTrip(@RequestBody TripRequest tripRequest){
        return tripService.addTrip(tripRequest);
    }

    @GetMapping("/viewAllTrip")
    public ResponseEntity<?> viewAllTrip() {
        return tripService.viewAllTrip();
    }

    @DeleteMapping("/delete/{trip_id}")
    public ResponseEntity<Response> deleteTrip(@PathVariable long trip_id){
        return tripService.deleteTrip(trip_id);
    }

    @PutMapping("/modifyTrip/{trip_id}")
    public ResponseEntity<Response> modifyTrip(
        @PathVariable long trip_id,
        @RequestBody TripRequest tripRequest
    ) {
        return tripService.modifyTrip(tripRequest, trip_id);
    }

    @PutMapping("/enrollTrip")
    public ResponseEntity<Response> entrollTrip(
        @RequestParam long trip_id, String email,
        @RequestBody TripDetail tripDetail
    ) {
        return tripService.enrollTrip(trip_id, email, tripDetail);
    }
    
    // @PutMapping("/{trip_id}/unenrollTrip/{email}")
    // public ResponseEntity<Response> unentrollTrip(
    //     @PathVariable long trip_id, String email
    // ) {
    //     return tripService.unenrollTrip(trip_id, email);
    // }

    // @PutMapping("/startTrip/{trip_id}")
    // public ResponseEntity<Response> modifyTrip(
    //     @PathVariable long trip_id
    // ) {
    //     // once started, cannot modify the trip
    //     return tripService.startTrip(trip_id);
    // }

    // @PutMapping("/endTrip/{trip_id}")
    // public ResponseEntity<Response> endTrip(
    //     @PathVariable long trip_id
    // ) {
    //     // once started, cannot modify the trip
    //     return tripService.endTrip(trip_id);
    // }


}
