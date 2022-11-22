package com.myfablo.seller.manage.discount.models.outlet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("discountId")
    @Expose
    private String discountId;
    @SerializedName("customId")
    @Expose
    private String customId;
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
    @SerializedName("isCustom")
    @Expose
    private Boolean isCustom;

    /**
     * No args constructor for use in serialization
     */
    public Items() {
    }

    /**
     * @param minAmount
     * @param discountTitle
     * @param discountPercent
     * @param isCustom
     * @param promoCode
     * @param discountId
     * @param customId
     * @param maxDiscount
     * @param isFlatDiscount
     */
    public Items(String discountId, String customId, String discountTitle, String promoCode, Integer discountPercent, Integer maxDiscount, Integer minAmount, Boolean isFlatDiscount, Boolean isCustom) {
        super();
        this.discountId = discountId;
        this.customId = customId;
        this.discountTitle = discountTitle;
        this.promoCode = promoCode;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.minAmount = minAmount;
        this.isFlatDiscount = isFlatDiscount;
        this.isCustom = isCustom;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
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

    public Boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

}