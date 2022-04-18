package com.deshaware.shuttleservice.repo;

import com.deshaware.shuttleservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM User WHERE Role = 'DRIVER' AND email = :email", nativeQuery=true)
    User findDriverByEmail(
        @Param("email") String email
        );

    // User findByUser(String email); // for trip
}
