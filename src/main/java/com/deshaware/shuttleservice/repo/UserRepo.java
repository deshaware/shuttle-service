package com.deshaware.shuttleservice.repo;

import com.deshaware.shuttleservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT * FROM User WHERE Role = 'USER' AND enabled=1 AND email = :email", nativeQuery=true)
    User findActiveUser(
        @Param("email") String email
        );

    @Modifying
    @Query(value = "DELETE FROM user WHERE email = :email", nativeQuery=true)
    void deleteByEmail(@Param("email") String email);
}
