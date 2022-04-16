package com.deshaware.shuttleservice.service;
import com.deshaware.shuttleservice.model.User;
import com.deshaware.shuttleservice.repo.UserRepo;

import java.time.Instant;
// import java.util.UUID;
import java.util.Optional;

import com.deshaware.shuttleservice.dto.RegisterRequest;


// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    
    public void signup(RegisterRequest regRequest) {
        Optional<User> check = userRepo.findByEmail(regRequest.getEmail());
        if (check.isPresent()) {
            throw new Error("User already exist with email:" + regRequest.getEmail());
        } else {
            User user = new User();
            user.setEmail(regRequest.getEmail());
            user.setName(regRequest.getName());
            user.setRole(regRequest.getRole());
            user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
            user.setModified(Instant.now());
            user.setEnabled(true);
            userRepo.save(user);
        }
        
    }
}
