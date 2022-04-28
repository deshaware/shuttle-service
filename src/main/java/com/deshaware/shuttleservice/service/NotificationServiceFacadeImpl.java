package com.deshaware.shuttleservice.service;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.helper.Coordinates;
import com.deshaware.shuttleservice.model.Trip;
import com.deshaware.shuttleservice.model.TripDetail;
import java.time.Instant;
import java.util.List;
import com.deshaware.shuttleservice.repo.TripDetailRepo;
import com.deshaware.shuttleservice.repo.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Transactional
@Service
public class NotificationServiceFacadeImpl implements NotificationServiceFacade {

    final static Logger logger = LogManager.getLogger(TripServiceImpl.class);

    // Facade Pattern using multiple services interfaces inside the class NotificationServiceFacadeImpl
    @Autowired
    TripRepo tripRepo;

    @Autowired
    TripDetailRepo tripDetailsRepo;

    @Autowired
    CurrentTripUserServiceAdapterImpl currentTripUserService;

    @Override
    public ResponseEntity<?> pushLocation(long trip_id, double lat, double lon){
        try {
            logger.info("Current location updated for trip: " + trip_id + " is " + lat + " " +lon);
            Trip trip = tripRepo.findById(trip_id).get();
            if (trip.equals(null)) {
                throw new Exception("Error in pushing location");
            }
            trip.setCurr_lat(lat);
            trip.setCurr_lon(lon);
            tripRepo.save(trip);
            Coordinates vehicleLocation = new Coordinates();
            vehicleLocation.setLat(lat);
            vehicleLocation.setLon(lon);

            List<TripDetail> tripDetailList = currentTripUserService.getCurrentTripUsers(trip_id);
            for (TripDetail tripDetail : tripDetailList) {
                this.updateTripDetailEstimation(tripDetail, vehicleLocation);
            }
            return new ResponseEntity<>("Location updated successfully",HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error in pushing location: " + trip_id + " is " + lat + " " +lon);
            return new ResponseEntity<>("Error in pushing location:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateTripDetailEstimation(TripDetail tripDetail, Coordinates vehicleLocation){
        try {
            logger.info("Updating User Location for user", tripDetail.getUser().getEmail());
            Coordinates userLocation = new Coordinates();
            userLocation.setLat(tripDetail.getStart_lat());
            userLocation.setLon(tripDetail.getEnd_long());
            Instant estimation = currentTripUserService.getTimeEstimation(userLocation, vehicleLocation);
            tripDetail.setEst_pickup(estimation);
            tripDetailsRepo.save(tripDetail);
        } catch (Exception e) {
            logger.error("Error in updating location");
        }
    }
}
