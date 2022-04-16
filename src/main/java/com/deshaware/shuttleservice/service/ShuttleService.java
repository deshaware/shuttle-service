package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import org.springframework.http.ResponseEntity;

public interface ShuttleService {

    public ResponseEntity<?> addShuttle(ShuttleRequest shuttle);

	// public Shuttle updateShuttle(Shuttle newShuttle);

	// public Iterable<Shuttle> getAllShuttle();

	// public ResponseEntity<?> findShuttleById(Long shuttle_id);
    
}
