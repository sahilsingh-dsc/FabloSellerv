package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("productId")
    @Expose
    private String productId;
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
    @SerializedName("displayPrice")
    @Expose
    private Float displayPrice;
    @SerializedName("hasCustomization")
    @Expose
    private Boolean hasCustomization;
    @SerializedName("customization")
    @Expose
    private List<Customization> customization = null;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param productDesc
     * @param productImage
     * @param productId
     * @param displayPrice
     * @param hasCustomization
     * @param customization
     * @param productName
     * @param productPrice
     */
    public Product(String productId, String productName, String productDesc, String productImage, Float productPrice, Float displayPrice, Boolean hasCustomization, List<Customization> customization) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.displayPrice = displayPrice;
        this.hasCustomization = hasCustomization;
        this.customization = customization;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public List<Customization> getCustomization() {
        return customization;
    }

    public void setCustomization(List<Customization> customization) {
        this.customization = customization;
    }

}