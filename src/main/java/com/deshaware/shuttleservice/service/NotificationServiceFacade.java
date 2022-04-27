package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.helper.Coordinates;
import com.deshaware.shuttleservice.model.TripDetail;

import org.springframework.http.ResponseEntity;

public interface NotificationServiceFacade {
    public ResponseEntity<?> pushLocation(long trip_id, double lat, double lon);

    // public void sendLocation(long trip_details_id);

    public void updateTripDetailEstimation(TripDetail tripDetail, Coordinates p);
}
