package com.deshaware.shuttleservice.repo;

import com.deshaware.shuttleservice.model.TripDetail;
import com.deshaware.shuttleservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDetailRepo extends JpaRepository<TripDetail, Long>{

    @Query(value = "SELECT td.* FROM trip_detail td JOIN trip t ON t.trip_id = td.trip_id " +
                    "WHERE td.trip_id = :trip_id AND t.trip_status = 'INTIATED' AND td.email = :email",
     nativeQuery=true)
    TripDetail findByExistingUser(
        @Param("trip_id") Long trip_id, 
        @Param("email") String email
    );
    
}
