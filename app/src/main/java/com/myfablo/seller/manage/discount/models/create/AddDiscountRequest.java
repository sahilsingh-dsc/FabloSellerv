package com.myfablo.seller.manage.discount.models.create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDiscountRequest {

    @SerializedName("discountTitle")
    @Expose
    private String discountTitle;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("discountPercent")
    @Expose
    private Integer discountPercent;
    @SerializedName("maxDiscount")
    @Expose
    private Integer maxDiscount;
    @SerializedName("minAmount")
    @Expose
    private Integer minAmount;
    @SerializedName("isFlatDiscount")
    @Expose
    private Boolean isFlatDiscount;

    /**
     * No args constructor for use in serialization
     */
    public AddDiscountRequest() {
    }

    /**
     * @param minAmount
     * @param discountTitle
     * @param discountPercent
     * @param promoCode
     * @param maxDiscount
     * @param isFlatDiscount
     */
    public AddDiscountRequest(String discountTitle, String promoCode, Integer discountPercent, Integer maxDiscount, Integer minAmount, Boolean isFlatDiscount) {
        super();
        this.discountTitle = discountTitle;
        this.promoCode = promoCode;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.minAmount = minAmount;
        this.isFlatDiscount = isFlatDiscount;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Integer maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Boolean getIsFlatDiscount() {
        return isFlatDiscount;
    }

    public void setIsFlatDiscount(Boolean isFlatDiscount) {
        this.isFlatDiscount = isFlatDiscount;
    }

}