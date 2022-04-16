package com.deshaware.shuttleservice.repo;

// import java.util.Optional;

import com.deshaware.shuttleservice.model.Shuttle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShuttleRepo extends JpaRepository<Shuttle, String>{
    // Optional<Shuttle> findShuttleById(String shuttle_id);
    
}
