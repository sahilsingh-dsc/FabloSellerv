package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllOrderResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private AllOrderResponseData allOrderResponseData;

    /**
     * No args constructor for use in serialization
     */
    public AllOrderResponse() {
    }

    /**
     * @param allOrderResponseData
     * @param message
     * @param status
     */
    public AllOrderResponse(String message, Integer status, AllOrderResponseData allOrderResponseData) {
        super();
        this.message = message;
        this.status = status;
        this.allOrderResponseData = allOrderResponseData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AllOrderResponseData getData() {
        return allOrderResponseData;
    }

    public void setData(AllOrderResponseData allOrderResponseData) {
        this.allOrderResponseData = allOrderResponseData;
    }

}