package com.deshaware.shuttleservice.service;

import java.util.List;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.model.Shuttle;

import org.springframework.http.ResponseEntity;

public interface ShuttleService {

    public ResponseEntity<?> addShuttle(ShuttleRequest shuttle);

    public ResponseEntity<?>  viewAllShuttle();

    public ResponseEntity<?> deleteShuttle(String shuttle_id);

	// public Shuttle updateShuttle(Shuttle newShuttle);

	// public Iterable<Shuttle> getAllShuttle();

	// public ResponseEntity<?> findShuttleById(Long shuttle_id);
    
}
