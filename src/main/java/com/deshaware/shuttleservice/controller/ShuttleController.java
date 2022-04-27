package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.ShuttleRequest;
import com.deshaware.shuttleservice.service.ShuttleService;
import org.springframework.http.HttpStatus;
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
        try {
            return shuttleService.addShuttle(shuttleRequest);   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Failed " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("viewAllShuttle")
    public ResponseEntity<?> viewAllShuttle(){
        try {
            return shuttleService.viewAllShuttle();
        } catch (Exception e) {
            return new ResponseEntity<>("Some error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("delete/{shuttle_id}")
    public ResponseEntity<?> deleteShuttle(@PathVariable("shuttle_id") String shuttle_id){
        try {
            return shuttleService.deleteShuttle(shuttle_id.toLowerCase());
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
