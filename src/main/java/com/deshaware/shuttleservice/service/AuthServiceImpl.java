package com.deshaware.shuttleservice.service;
import com.deshaware.shuttleservice.model.User;
import com.deshaware.shuttleservice.repo.UserRepo;
import com.deshaware.shuttleservice.response.Response;

import java.time.Instant;
import java.util.List;
// import java.util.UUID;
import java.util.Optional;

import com.deshaware.shuttleservice.dto.RegisterRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    
    public ResponseEntity<Response> signup(RegisterRequest regRequest) {
        try {
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
            
                return new ResponseEntity<Response>(new Response(){{
                    setData(user);
                    setStatus("SUCCESS");
                    setMessage("User Created Successfully!");
                }}, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setStatus("ERROR");
                setMessage("Failed to create user");
            }}, HttpStatus.BAD_REQUEST);
        }
        
    }

    public ResponseEntity<Response> getAllUsers() {
        try{
            List<User> users = userRepo.findAll();
            return new ResponseEntity<Response>(new Response(){{
                setData(users);
                setStatus("SUCCESS");
                setMessage("User Created Successfully!");
            }}, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setStatus("ERROR");
                setMessage("Failed to create user");
            }}, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Response> isUserActive(String email) {
        try{
            User user = userRepo.findActiveUser(email);
            if (user == null) {
                throw new Error("User not found: " + email);
            }
            String message = user.isEnabled() ? "Active" : "Inactive";
            return new ResponseEntity<Response>(new Response(){{
                setData("User " + email + " is " + message);
                setStatus("SUCCESS");
                setMessage("User Status");
            }}, HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setStatus("ERROR");
                setMessage("Failed to check user status");
            }}, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Response> deactiveUser(String email) {
        try{
            User user = userRepo.findActiveUser(email);
            if (user == null) {
                throw new Error("User not found: " + email);
            }
            if (!user.isEnabled()) {
                throw new Error("User is already deactivated!");
            }

            user.setEnabled(false);
            return new ResponseEntity<Response>(new Response(){{
                setData(user);
                setStatus("SUCCESS");
                setMessage("User is Deativated Succsessfully");
            }}, HttpStatus.ACCEPTED);
            
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(){{
                setData(e.getMessage());
                setStatus("ERROR");
                setMessage("Failed to deactivate user status");
            }}, HttpStatus.BAD_REQUEST);
        }
    }

    
}
