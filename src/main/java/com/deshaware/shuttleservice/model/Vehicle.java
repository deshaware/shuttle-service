package com.deshaware.shuttleservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Vehicle {

    @JsonProperty("shuttle")
    SHUTTLE,
    @JsonProperty("bus")
    BUS,
    @JsonProperty("car")
    CAR
    
}
