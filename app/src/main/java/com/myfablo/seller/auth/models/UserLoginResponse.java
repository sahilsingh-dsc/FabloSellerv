package com.myfablo.seller.auth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {

    public static final String RES_AUTH_REQUEST_ID = "reqId";
    public static final String RES_AUTH_PHONE = "phone";
    public static final String RES_AUTH_ONBOARD = "onboard";

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
    private UserLoginResponseItems items;

    /**
     * No args constructor for use in serialization
     */
    public UserLoginResponse() {
    }

    /**
     * @param subCode
     * @param message
     * @param error
     * @param items
     * @param status
     */
    public UserLoginResponse(Boolean status, Integer subCode, String message, String error, UserLoginResponseItems items) {
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

    public UserLoginResponseItems getItems() {
        return items;
    }

    public void setItems(UserLoginResponseItems items) {
        this.items = items;
    }

}