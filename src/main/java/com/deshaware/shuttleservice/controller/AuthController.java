package com.deshaware.shuttleservice.controller;

import com.deshaware.shuttleservice.dto.RegisterRequest;
import com.deshaware.shuttleservice.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.deshaware.shuttleservice.service.AuthServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody RegisterRequest register) { // transfered from DTO
        return authService.signup(register); 
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Response> getAllUsers() { // transfered from DTO
        return authService.getAllUsers(); 
    }


    @GetMapping("/isUserActive/{email}")
    public ResponseEntity<Response> isUserActive(@PathVariable("email") String email) { // transfered from DTO
        return authService.isUserActive(email); 
    }

   @PostMapping("/deactiveUser/{email}")
   public ResponseEntity<Response> deactiveUser(@PathVariable("email") String email) { // transfered from DTO
        return authService.deactiveUser(email);
   }



}
