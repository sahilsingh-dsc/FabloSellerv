package com.myfablo.seller.orders.model.order_get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amount {

    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("deliveryCharge")
    @Expose
    private Integer deliveryCharge;
    @SerializedName("deliveryTip")
    @Expose
    private Integer deliveryTip;
    @SerializedName("taxAmount")
    @Expose
    private Integer taxAmount;
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
    public Amount(Integer totalAmount, Integer deliveryCharge, Integer deliveryTip, Integer taxAmount, Integer discountedAmount) {
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

    public Integer getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Integer deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Integer getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(Integer deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Integer discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

}