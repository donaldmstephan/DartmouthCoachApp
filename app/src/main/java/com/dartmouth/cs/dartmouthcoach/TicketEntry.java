package com.dartmouth.cs.dartmouthcoach;

import java.util.Calendar;

/**
 * Created by Donald on 2/22/2016.
 */
public class TicketEntry {
    private Calendar mDateTime;
    private String mDepartureTime;
    private String mArrivalTime;
    private String mDepartureLocation;
    private String mArrivalLocation;


    public Calendar getDateTime() {
        return mDateTime;
    }

    public void setDateTime(Calendar mDateTime) {
        this.mDateTime = mDateTime;
    }

    public String getDepartureTime() {
        return mDepartureTime;
    }

    public void setDepartureTime(String mDepartureTime) {
        this.mDepartureTime = mDepartureTime;
    }

    public String getArrivalTime() {
        return mArrivalTime;
    }

    public void setArrivalTime(String mArrivalTime) {
        this.mArrivalTime = mArrivalTime;
    }

    public String getDepartureLocation() {
        return mDepartureLocation;
    }

    public void setDepartureLocation(String mDepartureLocation) {
        this.mDepartureLocation = mDepartureLocation;
    }

    public String getArrivalLocation() {
        return mArrivalLocation;
    }

    public void setArrivalLocation(String mArrivalLocation) {
        this.mArrivalLocation = mArrivalLocation;
    }
}
