package com.myfablo.seller.auth.models;

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
    @SerializedName("sellerId")
    @Expose
    private String sellerId;

    /**
     * No args constructor for use in serialization
     */
    public VerifyOtpResponseItems() {
    }

    /**
     * @param isPhoneLogin
     * @param token
     * @param isOnboarded
     * @param sellerId;
     */

    public VerifyOtpResponseItems(String token, Boolean isOnboarded, Boolean isPhoneLogin, String sellerId) {
        this.token = token;
        this.isOnboarded = isOnboarded;
        this.isPhoneLogin = isPhoneLogin;
        this.sellerId = sellerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getOnboarded() {
        return isOnboarded;
    }

    public void setOnboarded(Boolean onboarded) {
        isOnboarded = onboarded;
    }

    public Boolean getPhoneLogin() {
        return isPhoneLogin;
    }

    public void setPhoneLogin(Boolean phoneLogin) {
        isPhoneLogin = phoneLogin;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}