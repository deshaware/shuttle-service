package com.deshaware.shuttleservice.service;

import java.time.Instant;

import com.deshaware.shuttleservice.helper.Coordinates;

public interface EstimationService {
    public Instant getTimeEstimation(Coordinates p1, Coordinates p2);
    
}
