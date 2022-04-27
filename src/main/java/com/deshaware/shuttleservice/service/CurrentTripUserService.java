package com.deshaware.shuttleservice.service;
import java.util.List;
import com.deshaware.shuttleservice.model.TripDetail;

public interface CurrentTripUserService {
    public List<TripDetail> getCurrentTripUsers(long trip_id);

}
