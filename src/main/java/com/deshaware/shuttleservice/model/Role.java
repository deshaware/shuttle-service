package com.deshaware.shuttleservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("admin")
    ADMIN, 
    @JsonProperty("driver")
    DRIVER, 
    @JsonProperty("user")
    USER
}
