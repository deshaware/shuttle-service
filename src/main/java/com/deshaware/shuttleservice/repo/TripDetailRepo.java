package com.deshaware.shuttleservice.repo;

import java.util.HashSet;
import java.util.List;

import com.deshaware.shuttleservice.model.TripDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT td.* FROM trip_detail td JOIN trip t ON t.trip_id = td.trip_id " +
    "WHERE td.trip_id = :trip_id AND t.trip_status = :status",
        nativeQuery=true)
    List<TripDetail> findTripDetailByStatusAndTripId(
    @Param("trip_id") long trip_id, 
    @Param("status") String status
    );
    

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trip_detail td SET td.status= :tripDetailStatus WHERE td.trip_detail_id IN ( :trip_details_ids )",
     nativeQuery=true)
    void setStatusForTripDetails(@Param("trip_details_ids") HashSet<Long> trip_ids, String tripDetailStatus );

    
    
}
