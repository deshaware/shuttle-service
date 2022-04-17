package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.TripRequest;
import com.deshaware.shuttleservice.model.Trip;
import com.deshaware.shuttleservice.service.TripService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
}
