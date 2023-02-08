package com.myfablo.seller.orders.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timing {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     */
    public Timing() {
    }

    /**
     * @param date
     * @param time
     * @param status
     */
    public Timing(String status, String time, String date) {
        super();
        this.status = status;
        this.time = time;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}