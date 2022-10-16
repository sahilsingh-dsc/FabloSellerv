package com.myfablo.seller.auth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpResponseItems {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("isOnboarded")
    @Expose
    private Boolean isOnboarded;
    @SerializedName("isPhoneLogin")
    @Expose
    private Boolean isPhoneLogin;

    /**
     * No args constructor for use in serialization
     */
    public VerifyOtpResponseItems() {
    }

    /**
     * @param isPhoneLogin
     * @param token
     * @param isOnboarded
     */
    public VerifyOtpResponseItems(String token, Boolean isOnboarded, Boolean isPhoneLogin) {
        super();
        this.token = token;
        this.isOnboarded = isOnboarded;
        this.isPhoneLogin = isPhoneLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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