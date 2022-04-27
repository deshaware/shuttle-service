package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.model.TripStatus;
import com.deshaware.shuttleservice.helper.Coordinates;
import com.deshaware.shuttleservice.helper.DistanceAPI;
import com.deshaware.shuttleservice.model.TripDetail;

import java.time.Instant;
import java.util.List;
import com.deshaware.shuttleservice.repo.TripDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentTripUserServiceAdapterImpl implements CurrentTripUserService, EstimationService {
    @Autowired
    TripDetailRepo tripDetailsRepo;

    DistanceAPI distanceAPI;
    @Override
    public List<TripDetail> getCurrentTripUsers(long trip_id){
        // Implementing Adapter Pattern to use both
        return tripDetailsRepo.findTripDetailByStatusAndTripId(trip_id, TripStatus.IN_PROGRESS.toString());
    }


    public Instant getTimeEstimation(Coordinates p1, Coordinates p2){
        return distanceAPI.getEstimation(p1, p2);
    }
}
