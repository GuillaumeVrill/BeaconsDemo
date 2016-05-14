package com.guillaume.beaconsdemo;

import com.estimote.sdk.Beacon;

public class BeaconTimer {

    private int time;
    private Beacon beacon;

    public BeaconTimer(Beacon b, int t){
        this.beacon = b;
        this.time = t;
    }

    public int getTime(){
        return this.time;
    }

    public Beacon getBeacon(){
        return this.beacon;
    }

    public void setTime(int t){
        this.time = t;
    }

    public void setBeacon(Beacon b){
        this.beacon = b;
    }

    public boolean isAvailable(){
        if(time <= 0){
            return true;
        }
        else {
            return false;
        }
    }

}
