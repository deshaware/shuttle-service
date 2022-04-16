package com.deshaware.shuttleservice.dto;

import com.deshaware.shuttleservice.model.Vehicle;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShuttleRequest {
    private String shuttle_id;
    private Vehicle shuttle_type;
    private String shuttle_desc;
    private int capacity;
}
