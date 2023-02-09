package com.myfablo.seller.orders.model.order_get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentStatus")
    @Expose
    private String paymentStatus;

    /**
     * No args constructor for use in serialization
     */
    public Payment() {
    }

    /**
     * @param paymentMode
     * @param paymentStatus
     */
    public Payment(String paymentMode, String paymentStatus) {
        super();
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}