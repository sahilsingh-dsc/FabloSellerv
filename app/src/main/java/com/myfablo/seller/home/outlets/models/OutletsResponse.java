package com.myfablo.seller.home.outlets.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutletsResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("subCode")
    @Expose
    private Integer subCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("items")
    @Expose
    private List<OutletItem> outletItems = null;

    /**
     * No args constructor for use in serialization
     */
    public OutletsResponse() {
    }

    /**
     * @param subCode
     * @param message
     * @param error
     * @param outletItems
     * @param status
     */
    public OutletsResponse(Boolean status, Integer subCode, String message, String error, List<OutletItem> outletItems) {
        super();
        this.status = status;
        this.subCode = subCode;
        this.message = message;
        this.error = error;
        this.outletItems = outletItems;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<OutletItem> getItems() {
        return outletItems;
    }

    public void setItems(List<OutletItem> outletItems) {
        this.outletItems = outletItems;
    }

}