package com.deshaware.shuttleservice.service;


import com.deshaware.shuttleservice.dto.ShuttleRequest;

import org.springframework.http.ResponseEntity;

public interface ShuttleService {

    public ResponseEntity<?> addShuttle(ShuttleRequest shuttle);

    public ResponseEntity<?>  viewAllShuttle();

    public ResponseEntity<?> deleteShuttle(String shuttle_id);

	// public Shuttle updateShuttle(Shuttle newShuttle);
    
}
