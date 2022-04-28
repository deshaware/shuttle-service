package com.deshaware.shuttleservice.service;


import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface ShuttleService {

    public ResponseEntity<Response> addShuttle(ShuttleRequest shuttle);

    public ResponseEntity<Response>  viewAllShuttle();

    public ResponseEntity<Response> deleteShuttle(String shuttle_id);

	// public Shuttle updateShuttle(Shuttle newShuttle);
    
}
