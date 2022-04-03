package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.RegisterRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import com.deshaware.shuttleservice.service.AuthService;
import static org.springframework.http.HttpStatus.*;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest register) { // transfered from DTO
        try {
            authService.signup(register);
            return new ResponseEntity<>("User Registration Successful",
                    OK);    
        } catch (Exception e) {
            return new ResponseEntity<String>("You've Failed" + e.getMessage(), HttpStatus.FORBIDDEN);
        }
        
    }


    // @RequestMapping(value="/", method= RequestMethod.GET)
    // @ResponseBody
    // public String login() {
    //     System.out.println("Called Login");
    //     return "Hello World!";
    // }


}
