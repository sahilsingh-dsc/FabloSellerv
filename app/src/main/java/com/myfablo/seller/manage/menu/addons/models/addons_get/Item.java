package com.myfablo.seller.manage.menu.addons.models.addons_get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("addOnCategoryId")
    @Expose
    private String addOnCategoryId;
    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("minSelection")
    @Expose
    private Integer minSelection;
    @SerializedName("maxSelection")
    @Expose
    private Integer maxSelection;
    @SerializedName("productList")
    @Expose
    private List<Product> productList;

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param minSelection
     * @param outletId
     * @param maxSelection
     * @param addOnCategoryId
     * @param categoryName
     * @param productList
     */
    public Item(String addOnCategoryId, String outletId, String categoryName, Integer minSelection, Integer maxSelection, List<Product> productList) {
        super();
        this.addOnCategoryId = addOnCategoryId;
        this.outletId = outletId;
        this.categoryName = categoryName;
        this.minSelection = minSelection;
        this.maxSelection = maxSelection;
        this.productList = productList;
    }

    public String getAddOnCategoryId() {
        return addOnCategoryId;
    }

    public void setAddOnCategoryId(String addOnCategoryId) {
        this.addOnCategoryId = addOnCategoryId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getMinSelection() {
        return minSelection;
    }

    public void setMinSelection(Integer minSelection) {
        this.minSelection = minSelection;
    }

    public Integer getMaxSelection() {
        return maxSelection;
    }

    public void setMaxSelection(Integer maxSelection) {
        this.maxSelection = maxSelection;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}