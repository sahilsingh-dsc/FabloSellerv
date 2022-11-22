package com.myfablo.seller.auth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpResponse {

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
    private VerifyOtpResponseItems items;

    /**
     * No args constructor for use in serialization
     */
    public VerifyOtpResponse() {
    }

    /**
     * @param subCode
     * @param message
     * @param error
     * @param items
     * @param status
     */
    public VerifyOtpResponse(Boolean status, Integer subCode, String message, String error, VerifyOtpResponseItems items) {
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

    public VerifyOtpResponseItems getItems() {
        return items;
    }

    public void setItems(VerifyOtpResponseItems items) {
        this.items = items;
    }

}