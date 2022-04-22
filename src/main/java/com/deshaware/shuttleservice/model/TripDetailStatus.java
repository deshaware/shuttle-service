package com.deshaware.shuttleservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TripDetailStatus {
    @JsonProperty("REGISTERED")
    REGISTERED,
    @JsonProperty("ON_THE_WAY")
    ON_THE_WAY,
    @JsonProperty("IN_TRANSIT")
    IN_TRANSIT,
    @JsonProperty("COMPLETED")
    COMPLETED,
    @JsonProperty("CANCELED")
    CANCELED
}
