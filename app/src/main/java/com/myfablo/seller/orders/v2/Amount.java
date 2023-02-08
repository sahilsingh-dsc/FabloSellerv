package com.myfablo.seller.orders.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amount {

    @SerializedName("totalAmount")
    @Expose
    private Float totalAmount;
    @SerializedName("deliveryCharge")
    @Expose
    private Float deliveryCharge;
    @SerializedName("deliveryTip")
    @Expose
    private Float deliveryTip;
    @SerializedName("taxAmount")
    @Expose
    private Float taxAmount;
    @SerializedName("discountedAmount")
    @Expose
    private Float discountedAmount;

    /**
     * No args constructor for use in serialization
     */
    public Amount() {
    }

    /**
     * @param discountedAmount
     * @param totalAmount
     * @param deliveryTip
     * @param deliveryCharge
     * @param taxAmount
     */

    public Amount(Float totalAmount, Float deliveryCharge, Float deliveryTip, Float taxAmount, Float discountedAmount) {
        this.totalAmount = totalAmount;
        this.deliveryCharge = deliveryCharge;
        this.deliveryTip = deliveryTip;
        this.taxAmount = taxAmount;
        this.discountedAmount = discountedAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Float getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(Float deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Float getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Float discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
}