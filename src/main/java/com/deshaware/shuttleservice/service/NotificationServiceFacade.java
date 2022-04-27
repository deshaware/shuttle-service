package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.helper.Coordinates;
import com.deshaware.shuttleservice.model.TripDetail;

public interface NotificationServiceFacade {
    public void pushLocation(long trip_id, double lat, double lon);

    // public void sendLocation(long trip_details_id);

    public void updateTripDetailEstimation(TripDetail tripDetail, Coordinates p);
}
