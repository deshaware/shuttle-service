package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.TripRequest;
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

    @GetMapping("/viewTrip/{trip_id}")
    public ResponseEntity<?> viewTrip(@PathVariable long trip_id) {
        return tripService.viewTrip(trip_id);
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

   


}
