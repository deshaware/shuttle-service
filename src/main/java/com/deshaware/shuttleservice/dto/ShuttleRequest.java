package com.deshaware.shuttleservice.dto;

import com.deshaware.shuttleservice.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleRequest {
    private String shuttle_id;
    private Vehicle shuttle_type;
    private String shuttle_desc;
    private int capacity;

    public String toJSONString(){
        return 
        "{\"shuttle_id\" : \"" + this.shuttle_id+ "\", \"shuttle_desc\" : \"" + 
        this.shuttle_desc+ "\", \"capacity\" : \"" + this.capacity 
        + "\", \"shuttle_type\" : \"" + this.shuttle_type.toString().toLowerCase()+ "\"}";
    }
}
