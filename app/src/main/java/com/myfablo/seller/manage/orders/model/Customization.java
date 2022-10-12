package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customization {

    @SerializedName("customItemId")
    @Expose
    private String customItemId;
    @SerializedName("customItemName")
    @Expose
    private String customItemName;
    @SerializedName("customItemPrice")
    @Expose
    private Integer customItemPrice;
    @SerializedName("isDefault")
    @Expose
    private Boolean isDefault;

    /**
     * No args constructor for use in serialization
     */
    public Customization() {
    }

    /**
     * @param isDefault
     * @param customItemName
     * @param customItemId
     * @param customItemPrice
     */
    public Customization(String customItemId, String customItemName, Integer customItemPrice, Boolean isDefault) {
        super();
        this.customItemId = customItemId;
        this.customItemName = customItemName;
        this.customItemPrice = customItemPrice;
        this.isDefault = isDefault;
    }

    public String getCustomItemId() {
        return customItemId;
    }

    public void setCustomItemId(String customItemId) {
        this.customItemId = customItemId;
    }

    public String getCustomItemName() {
        return customItemName;
    }

    public void setCustomItemName(String customItemName) {
        this.customItemName = customItemName;
    }

    public Integer getCustomItemPrice() {
        return customItemPrice;
    }

    public void setCustomItemPrice(Integer customItemPrice) {
        this.customItemPrice = customItemPrice;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}