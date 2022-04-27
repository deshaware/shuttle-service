package com.deshaware.shuttleservice.helper;

import java.util.List;

import com.deshaware.shuttleservice.model.TripDetail;

class Coordinate {
    public double lat;
    public double lon;   
}

public class Distance extends Coordinate {
    private Coordinate p1;
    private Coordinate p2;

    public Coordinate getP1() {
        return p1;
    }

    public Coordinate getP2() {
        return p2;
    }

    public void setP1(Coordinate p1) {
        this.p1 = p1;
    }

    public void setP2(Coordinate p2) {
        this.p2 = p2;
    }

    /**
     * 
     * @param p1 First Coordinate
     * @param p2 Second Coordinate
     * @return Returns Manhattan Distance between two coordinates
     */
    public double distance(Coordinate p1, Coordinate p2){
        return Math.abs(Math.abs(p1.lat - p2.lat) - Math.abs(p1.lon - p2.lon));
    }
    
}
class CalculateETA {
    // public List<TripDetail> tripDetails;
    public List<Distance> distances;

    public void setDistances(List<TripDetail> tripDetails) {
        for (TripDetail tripDetail : tripDetails) {
            double start_lat = tripDetail.getStart_lat();
            double start_lon = tripDetail.getStart_long();
            double end_lat = tripDetail.getEnd_lat();
            double end_lon = tripDetail.getEnd_long();

        }
    }

    public List<Distance> getDistances() {
        return distances;
    }



    // public List<TripDetail> calculateETA(){
        // for(TripDetail trip: tripDetails){

        // }
}

