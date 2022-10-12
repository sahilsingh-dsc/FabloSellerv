package com.myfablo.seller.home.outlets.models.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutletDetailsResponse {

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
    private OutletDetailsItem items;

    /**
     * No args constructor for use in serialization
     */
    public OutletDetailsResponse() {
    }

    /**
     * @param subCode
     * @param message
     * @param error
     * @param items
     * @param status
     */
    public OutletDetailsResponse(Boolean status, Integer subCode, String message, String error, OutletDetailsItem items) {
        super();
        this.status = status;
        this.subCode = subCode;
        this.message = message;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public OutletDetailsItem getItems() {
        return items;
    }

    public void setItems(OutletDetailsItem items) {
        this.items = items;
    }

}