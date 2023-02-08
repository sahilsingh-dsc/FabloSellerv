package com.myfablo.seller.orders.model.order_get;

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
    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("isVeg")
    @Expose
    private Boolean isVeg;
    @SerializedName("quantityPrice")
    @Expose
    private Integer quantityPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("addOnName")
    @Expose
    private String addOnName;
    @SerializedName("addOnIdList")
    @Expose
    private List<String> addOnIdList;
    @SerializedName("variationName")
    @Expose
    private String variationName;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param isVeg
     * @param quantity
     * @param variationName
     * @param productId
     * @param quantityPrice
     * @param addOnIdList
     * @param productName
     * @param productPrice
     * @param addOnName
     */
    public Product(String productId, String productName, Integer productPrice, Boolean isVeg, Integer quantityPrice, Integer quantity, String addOnName, List<String> addOnIdList, String variationName) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isVeg = isVeg;
        this.quantityPrice = quantityPrice;
        this.quantity = quantity;
        this.addOnName = addOnName;
        this.addOnIdList = addOnIdList;
        this.variationName = variationName;
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

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    public Integer getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(Integer quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAddOnName() {
        return addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public List<String> getAddOnIdList() {
        return addOnIdList;
    }

    public void setAddOnIdList(List<String> addOnIdList) {
        this.addOnIdList = addOnIdList;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

}