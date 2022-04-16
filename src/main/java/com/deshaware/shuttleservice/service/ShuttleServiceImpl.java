package com.deshaware.shuttleservice.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.model.Shuttle;
import com.deshaware.shuttleservice.repo.ShuttleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ShuttleServiceImpl implements ShuttleService {
    
    @Autowired
    ShuttleRepo shuttleRepo;

    @Override
    public ResponseEntity<?> addShuttle(ShuttleRequest shuttle) {
        try {
            Optional<Shuttle> checkShuttle = shuttleRepo.findById(shuttle.getShuttle_id());
            if (checkShuttle.isPresent()) {
                // throw error
                // throw new Error("shuttle with " + shuttle.getShuttle_id() + " is already exist");
                return new ResponseEntity<>("Already exist", HttpStatus.CONFLICT);
            }
            Shuttle newShuttle = new Shuttle();
            newShuttle.setCapacity(shuttle.getCapacity());
            newShuttle.setShuttle_id(shuttle.getShuttle_id().toLowerCase());
            newShuttle.setShuttle_desc(shuttle.getShuttle_desc());
            newShuttle.setSuttle_type(shuttle.getShuttle_type());
            newShuttle.setEnabled(true);
            newShuttle.setModified(Instant.now());
            shuttleRepo.save(newShuttle);
            return new ResponseEntity<>("Shuttle Added Succesfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> viewAllShuttle() {
        List<Shuttle> shuttleList = shuttleRepo.findAll();
        if (shuttleList.isEmpty()) {
            return new ResponseEntity<>("No Shuttles Found", HttpStatus.NOT_FOUND);
        }
        System.out.println(shuttleList);
        // return shuttleList;
        return new ResponseEntity<>(shuttleList, HttpStatus.ACCEPTED);
        
    }

    @Override
    public ResponseEntity<?> deleteShuttle(String shuttle_id) {
        Optional<Shuttle> getShuttle = shuttleRepo.findById(shuttle_id);
        if (!getShuttle.isPresent()) {
            throw new Error("No Shuttle Found with id" + shuttle_id);
        }
        shuttleRepo.deleteById(shuttle_id);
        return new ResponseEntity<>("Shuttle Deleted Succesfully",HttpStatus.ACCEPTED);
    }

}
