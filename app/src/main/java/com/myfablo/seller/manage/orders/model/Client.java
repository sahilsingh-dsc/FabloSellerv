package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("clientAddress")
    @Expose
    private String clientAddress;
    @SerializedName("clientLongitude")
    @Expose
    private Float clientLongitude;
    @SerializedName("clientLatitude")
    @Expose
    private Float clientLatitude;

    /**
     * No args constructor for use in serialization
     */
    public Client() {
    }

    /**
     * @param clientId
     * @param clientName
     * @param clientLongitude
     * @param clientAddress
     * @param clientLatitude
     */
    public Client(String clientId, String clientName, String clientAddress, Float clientLongitude, Float clientLatitude) {
        super();
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientLongitude = clientLongitude;
        this.clientLatitude = clientLatitude;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Float getClientLongitude() {
        return clientLongitude;
    }

    public void setClientLongitude(Float clientLongitude) {
        this.clientLongitude = clientLongitude;
    }

    public Float getClientLatitude() {
        return clientLatitude;
    }

    public void setClientLatitude(Float clientLatitude) {
        this.clientLatitude = clientLatitude;
    }

}