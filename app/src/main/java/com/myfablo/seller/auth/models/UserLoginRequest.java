package com.myfablo.seller.auth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginRequest {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("userType")
    @Expose
    private String userType;

    /**
     * No args constructor for use in serialization
     */
    public UserLoginRequest() {
    }

    /**
     * @param phone
     * @param userType
     */
    public UserLoginRequest(String phone, String userType) {
        super();
        this.phone = phone;
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}