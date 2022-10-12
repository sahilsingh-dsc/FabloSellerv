package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amount {

    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("deliveryCharge")
    @Expose
    private Float deliveryCharge;
    @SerializedName("deliveryTip")
    @Expose
    private Integer deliveryTip;
    @SerializedName("taxAmount")
    @Expose
    private Float taxAmount;
    @SerializedName("discountedAmount")
    @Expose
    private Integer discountedAmount;

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
    public Amount(Integer totalAmount, Float deliveryCharge, Integer deliveryTip, Float taxAmount, Integer discountedAmount) {
        super();
        this.totalAmount = totalAmount;
        this.deliveryCharge = deliveryCharge;
        this.deliveryTip = deliveryTip;
        this.taxAmount = taxAmount;
        this.discountedAmount = discountedAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Integer getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(Integer deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Integer discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

}