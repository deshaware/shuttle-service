package com.deshaware.shuttleservice.service;

import java.time.Instant;
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
            newShuttle.setShuttle_id(shuttle.getShuttle_id());
            newShuttle.setShuttle_desc(shuttle.getShuttle_desc());
            newShuttle.setSuttle_type(shuttle.getShuttle_type());
            newShuttle.setEnabled(true);
            newShuttle.setModified(Instant.now());
            shuttleRepo.save(newShuttle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error", HttpStatus.FORBIDDEN);
        }
        
    }

}
