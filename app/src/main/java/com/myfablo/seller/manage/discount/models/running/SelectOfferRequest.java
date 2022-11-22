package com.myfablo.seller.manage.discount.models.running;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectOfferRequest {

    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("discountId")
    @Expose
    private String discountId;

    /**
     * No args constructor for use in serialization
     */
    public SelectOfferRequest() {
    }

    /**
     * @param outletId
     * @param discountId
     */
    public SelectOfferRequest(String outletId, String discountId) {
        super();
        this.outletId = outletId;
        this.discountId = discountId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

}