package com.myfablo.seller.manage.orders.model;

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
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productAmount")
    @Expose
    private Float productAmount;
    @SerializedName("hasCustomization")
    @Expose
    private Boolean hasCustomization;
    @SerializedName("customization")
    @Expose
    private List<Customization> customization = null;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("totalPrice")
    @Expose
    private Float totalPrice;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param productAmount
     * @param productImage
     * @param quantity
     * @param productId
     * @param hasCustomization
     * @param customization
     * @param totalPrice
     * @param productName
     */
    public Product(String productId, String productName, String productImage, Float productAmount, Boolean hasCustomization, List<Customization> customization, Integer quantity, Float totalPrice) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productAmount = productAmount;
        this.hasCustomization = hasCustomization;
        this.customization = customization;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Float getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Float productAmount) {
        this.productAmount = productAmount;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

}