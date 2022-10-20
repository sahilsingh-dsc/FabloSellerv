package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Outlet {

    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("outletName")
    @Expose
    private String outletName;
    @SerializedName("outletArea")
    @Expose
    private String outletArea;
    @SerializedName("outletAddress")
    @Expose
    private String outletAddress;
    @SerializedName("outletLongitude")
    @Expose
    private Float outletLongitude;
    @SerializedName("outletLatitude")
    @Expose
    private Float outletLatitude;

    /**
     * No args constructor for use in serialization
     */
    public Outlet() {
    }

    /**
     * @param outletName
     * @param outletLatitude
     * @param outletAddress
     * @param outletLongitude
     * @param outletId
     */
    public Outlet(String outletId, String outletName, String outletArea, String outletAddress, Float outletLongitude, Float outletLatitude) {
        super();
        this.outletId = outletId;
        this.outletName = outletName;
        this.outletArea = outletArea;
        this.outletAddress = outletAddress;
        this.outletLongitude = outletLongitude;
        this.outletLatitude = outletLatitude;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletArea() {
        return outletArea;
    }

    public void setOutletArea(String outletArea) {
        this.outletArea = outletArea;
    }

    public String getOutletAddress() {
        return outletAddress;
    }

    public void setOutletAddress(String outletAddress) {
        this.outletAddress = outletAddress;
    }

    public Float getOutletLongitude() {
        return outletLongitude;
    }

    public void setOutletLongitude(Float outletLongitude) {
        this.outletLongitude = outletLongitude;
    }

    public Float getOutletLatitude() {
        return outletLatitude;
    }

    public void setOutletLatitude(Float outletLatitude) {
        this.outletLatitude = outletLatitude;
    }

}