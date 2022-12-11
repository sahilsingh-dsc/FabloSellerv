package com.myfablo.seller.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppVersionResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("subCode")
    @Expose
    private Integer subCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private Float items;

    /**
     * No args constructor for use in serialization
     */
    public AppVersionResponse() {
    }

    /**
     * @param subCode
     * @param message
     * @param items
     * @param status
     */
    public AppVersionResponse(Boolean status, Integer subCode, String message, Float items) {
        super();
        this.status = status;
        this.subCode = subCode;
        this.message = message;
        this.items = items;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCode(Integer subCode) {
        this.subCode = subCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getItems() {
        return items;
    }

    public void setItems(Float items) {
        this.items = items;
    }

}