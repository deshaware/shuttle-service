package com.deshaware.shuttleservice.dto;
import java.time.Instant;

import com.deshaware.shuttleservice.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TripDetailRequest {

    private long trip_detail_id; // system generated
    private long trip_id; // from a trip
    private User email; // email id
    
    private double start_long; 
    private double start_lat;
    private double end_long;
    private double end_lat;

    private Instant est_pickup;
    private int waitlist;

    private TripStatus trip_status;
}
