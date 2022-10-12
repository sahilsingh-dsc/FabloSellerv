package com.myfablo.seller.auth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponseItems {

    @SerializedName("reqId")
    @Expose
    private String reqId;
    @SerializedName("isOnboarded")
    @Expose
    private Boolean isOnboarded;
    @SerializedName("isPhoneLogin")
    @Expose
    private Boolean isPhoneLogin;

    /**
     * No args constructor for use in serialization
     */
    public UserLoginResponseItems() {
    }

    /**
     * @param isPhoneLogin
     * @param reqId
     * @param isOnboarded
     */
    public UserLoginResponseItems(String reqId, Boolean isOnboarded, Boolean isPhoneLogin) {
        super();
        this.reqId = reqId;
        this.isOnboarded = isOnboarded;
        this.isPhoneLogin = isPhoneLogin;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Boolean getIsOnboarded() {
        return isOnboarded;
    }

    public void setIsOnboarded(Boolean isOnboarded) {
        this.isOnboarded = isOnboarded;
    }

    public Boolean getIsPhoneLogin() {
        return isPhoneLogin;
    }

    public void setIsPhoneLogin(Boolean isPhoneLogin) {
        this.isPhoneLogin = isPhoneLogin;
    }

}