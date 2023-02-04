package com.myfablo.seller.manage.menu.addons.models.addons_get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("addOnCategoryId")
    @Expose
    private String addOnCategoryId;
    @SerializedName("addOnProductId")
    @Expose
    private String addOnProductId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productPrice")
    @Expose
    private Float productPrice;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param addOnProductId
     * @param addOnCategoryId
     * @param productName
     * @param productPrice
     */
    public Product(String addOnCategoryId, String addOnProductId, String productName, Float productPrice) {
        super();
        this.addOnCategoryId = addOnCategoryId;
        this.addOnProductId = addOnProductId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getAddOnCategoryId() {
        return addOnCategoryId;
    }

    public void setAddOnCategoryId(String addOnCategoryId) {
        this.addOnCategoryId = addOnCategoryId;
    }

    public String getAddOnProductId() {
        return addOnProductId;
    }

    public void setAddOnProductId(String addOnProductId) {
        this.addOnProductId = addOnProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

}