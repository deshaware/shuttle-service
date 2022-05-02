package com.deshaware.shuttleservice.dto;

import java.time.Instant;

import com.deshaware.shuttleservice.model.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
    /**
     *  driver email
     */
    private long trip_id; // for my reference, might delete later
    private String driver_id; 
    private String shuttle_id;
    private Instant scheduled_on;
    private double start_lang;
    private double start_lat;
    private TripStatus trip_status;

    public String toJSONString(){
        return "{ \"driver_id\": \""+ this.driver_id + 
            "\", \"shuttle_id\": \"" + this.shuttle_id + 
            "\", \"scheduled_on\": \" " + this.scheduled_on + 
            "\", \"start_lang\": \"" + this.start_lang + 
            "\", \"start_lat\": \"" + this.start_lat + 
            "\" }";
    }
}
