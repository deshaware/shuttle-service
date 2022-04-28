package com.deshaware.shuttleservice.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.model.Shuttle;
import com.deshaware.shuttleservice.repo.ShuttleRepo;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Transactional
public class ShuttleServiceImpl implements ShuttleService {
   
    final static Logger logger = LogManager.getLogger(TripServiceImpl.class);

    @Autowired
    ShuttleRepo shuttleRepo;

    @Override
    public ResponseEntity<Response> addShuttle(ShuttleRequest shuttle) {
        try {
            logger.info("Adding new shuttle");
            Optional<Shuttle> checkShuttle = shuttleRepo.findById(shuttle.getShuttle_id());
            if (checkShuttle.isPresent()) {
                throw new Exception("Already exist");
            }
            Shuttle newShuttle = new Shuttle();
            newShuttle.setCapacity(shuttle.getCapacity());
            newShuttle.setShuttle_id(shuttle.getShuttle_id().toLowerCase());
            newShuttle.setShuttle_desc(shuttle.getShuttle_desc());
            newShuttle.setSuttle_type(shuttle.getShuttle_type());
            newShuttle.setEnabled(true);
            newShuttle.setModified(Instant.now());
            shuttleRepo.save(newShuttle);

            return new ResponseEntity<Response>(new Response(){{
                setData(newShuttle);
                setMessage("Shuttle Added Succesfully");
                setStatus("SUCCESS");
            }} , HttpStatus.CREATED);

        } 
        catch (Exception e) {
            logger.error("Error while adding shuttle");
            return new ResponseEntity<Response>(new Response(){{
                setMessage("Error while adding shuttle");
                setStatus("ERROR");
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> viewAllShuttle() {
        try {
            List<Shuttle> shuttleList = shuttleRepo.findAll();
            if (shuttleList.isEmpty()) {
                throw new Exception("No Shuttles Found");
            }
            return new ResponseEntity<Response>(new Response(){{
                setData(shuttleList);
                setMessage("Shuttle Fetched Succesfully");
                setStatus("SUCCESS");
            }} , HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            logger.error("Error while fetching shuttle");
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setMessage("Error while fetching shuttle");
                setStatus("ERROR");
            }} , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @Override
    public ResponseEntity<Response> deleteShuttle(String shuttle_id) {
        try {
            Optional<Shuttle> getShuttle = shuttleRepo.findById(shuttle_id);
            if (!getShuttle.isPresent()) {
                throw new Exception("No Shuttle Found with id" + shuttle_id);
            }
            shuttleRepo.deleteById(shuttle_id);

            return new ResponseEntity<Response>(new Response(){{
                setMessage("Shuttle Deleted Succesfully");
                setStatus("SUCCESS");
            }} , HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            logger.error("Error while deleting shuttle");
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setMessage("Error while deleting shuttle");
                setStatus("ERROR");
            }} , HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}