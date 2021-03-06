package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.service.ShuttleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/shuttle")
@AllArgsConstructor
public class ShuttleController {
    private final ShuttleService shuttleService;

    @PostMapping("/addShuttle")
    public ResponseEntity<?> addShuttle(@RequestBody ShuttleRequest shuttleRequest) { // transfered from DTO
        return shuttleService.addShuttle(shuttleRequest);   
    }

    @GetMapping("/viewAllShuttle")
    public ResponseEntity<?> viewAllShuttle(){
        return shuttleService.viewAllShuttle();
    }
    
    @DeleteMapping("/delete/{shuttle_id}")
    public ResponseEntity<?> deleteShuttle(@PathVariable("shuttle_id") String shuttle_id){
        return shuttleService.deleteShuttle(shuttle_id.toLowerCase());
    }

}
