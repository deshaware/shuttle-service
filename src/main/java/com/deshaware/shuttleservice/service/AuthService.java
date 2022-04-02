package com.deshaware.shuttleservice.service;
import com.deshaware.shuttleservice.model.User;

import java.time.Instant;

import com.deshaware.shuttleservice.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    public void signup(RegisterRequest regRequest) {
        User user = new User();
        user.setEmail(regRequest.getEmail());
        user.setName(regRequest.getName());
        user.setRole(regRequest.getRole());
        user.setPassword(regRequest.getPassword());
        user.setModified(Instant.now());
        user.setEnabled(true);
    }
}
