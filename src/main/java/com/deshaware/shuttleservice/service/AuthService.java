package com.deshaware.shuttleservice.service;

import com.deshaware.shuttleservice.dto.RegisterRequest;
import com.deshaware.shuttleservice.response.Response;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<Response> signup(RegisterRequest regRequest);
    public ResponseEntity<Response> getAllUsers();
    public ResponseEntity<Response> isUserActive(String email);
    public ResponseEntity<Response> deactiveUser(String email);
    public ResponseEntity<Response> deleteUser(String email);

}
