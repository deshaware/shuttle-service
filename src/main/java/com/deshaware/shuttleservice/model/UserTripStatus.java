package com.deshaware.shuttleservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserTripStatus {
    @JsonProperty("REGISTERED")
    REGISTERED,
    @JsonProperty("IN_TRANSIT")
    IN_TRANSIT,
    @JsonProperty("COMPLETED")
    COMPLETED,
    @JsonProperty("CANCELED")
    CANCELED
}
