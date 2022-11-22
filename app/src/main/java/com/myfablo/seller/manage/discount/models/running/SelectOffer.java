package com.myfablo.seller.manage.discount.models.running;

public class SelectOffer {

    private String outletId;
    private String discountId;

    public SelectOffer() {
    }

    public SelectOffer(String outletId, String discountId) {
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
