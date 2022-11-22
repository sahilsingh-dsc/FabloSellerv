package com.myfablo.seller.auth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {

    @SerializedName("reqId")
    @Expose
    private String reqId;
    @SerializedName("otp")
    @Expose
    private String otp;

    /**
     * No args constructor for use in serialization
     */
    public VerifyOtpRequest() {
    }

    /**
     * @param otp
     * @param reqId
     */
    public VerifyOtpRequest(String reqId, String otp) {
        super();
        this.reqId = reqId;
        this.otp = otp;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}