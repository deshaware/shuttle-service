package com.deshaware.shuttleservice.service;

import java.time.Instant;
import java.util.List;
import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.helper.Coordinates;

public interface CurrentTripUserService {
    public List<TripDetail> getCurrentTripUsers(long trip_id);

    public Instant getTimeEstimation(Coordinates p1, Coordinates p2);
}
