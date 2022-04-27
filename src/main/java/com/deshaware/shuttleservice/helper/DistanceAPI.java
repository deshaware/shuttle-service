package com.deshaware.shuttleservice.helper;

import java.time.Instant;
import ch.qos.logback.core.util.Duration;

public class DistanceAPI {
    /**
     * 
     * @param p1 First Coordinate
     * @param p2 Second Coordinate
     * @return Returns Manhattan Distance between two coordinates
     */
    public double distance(Coordinates p1, Coordinates p2){
        return Math.abs(Math.abs(p1.lat - p2.lat) - Math.abs(p1.lon - p2.lon));
    }

    public Instant getEstimation(Coordinates p1, Coordinates p2){
        long duration = Duration.buildBySeconds((long) (this.distance(p1, p2) / 30)).getMilliseconds();
        return Instant.now().plusSeconds(duration);
    }
    
}