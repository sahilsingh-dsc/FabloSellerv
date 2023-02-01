package com.myfablo.seller.manage.menu.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("parentCategoryId")
    @Expose
    private String parentCategoryId;
    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productDesc")
    @Expose
    private String productDesc;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productPrice")
    @Expose
    private Float productPrice;
    @SerializedName("isVeg")
    @Expose
    private Boolean isVeg;
    @SerializedName("inStock")
    @Expose
    private Boolean inStock;
    @SerializedName("displayPrice")
    @Expose
    private Float displayPrice;
    @SerializedName("hasCustomization")
    @Expose
    private Boolean hasCustomization;
    @SerializedName("hasAddOn")
    @Expose
    private Boolean hasAddOn;

    /**
     * No args constructor for use in serialization
     */
    public Items() {
    }

    /**
     * @param productDesc
     * @param hasAddOn
     * @param productImage
     * @param isVeg
     * @param productId
     * @param displayPrice
     * @param hasCustomization
     * @param outletId
     * @param parentCategoryId
     * @param inStock
     * @param productName
     * @param productPrice
     */
    public Items(String productId, String parentCategoryId, String outletId, String productName, String productDesc, String productImage, Float productPrice, Boolean isVeg, Boolean inStock, Float displayPrice, Boolean hasCustomization, Boolean hasAddOn) {
        super();
        this.productId = productId;
        this.parentCategoryId = parentCategoryId;
        this.outletId = outletId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.isVeg = isVeg;
        this.inStock = inStock;
        this.displayPrice = displayPrice;
        this.hasCustomization = hasCustomization;
        this.hasAddOn = hasAddOn;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Float getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(Float displayPrice) {
        this.displayPrice = displayPrice;
    }

    public Boolean getHasCustomization() {
        return hasCustomization;
    }

    public void setHasCustomization(Boolean hasCustomization) {
        this.hasCustomization = hasCustomization;
    }

    public Boolean getHasAddOn() {
        return hasAddOn;
    }

    public void setHasAddOn(Boolean hasAddOn) {
        this.hasAddOn = hasAddOn;
    }

}