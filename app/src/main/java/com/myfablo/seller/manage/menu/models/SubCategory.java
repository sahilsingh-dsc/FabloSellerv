package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryDesc")
    @Expose
    private String categoryDesc;
    @SerializedName("categoryImage")
    @Expose
    private String categoryImage;
    @SerializedName("hasSubCategory")
    @Expose
    private Boolean hasSubCategory;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("productList")
    @Expose
    private List<Product> productList = null;

    /**
     * No args constructor for use in serialization
     */
    public SubCategory() {
    }

    /**
     * @param categoryImage
     * @param hasSubCategory
     * @param categoryName
     * @param categoryId
     * @param productList
     * @param categoryDesc
     * @param itemCount
     */
    public SubCategory(String categoryId, String categoryName, String categoryDesc, String categoryImage, Boolean hasSubCategory, Integer itemCount, List<Product> productList) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryImage = categoryImage;
        this.hasSubCategory = hasSubCategory;
        this.itemCount = itemCount;
        this.productList = productList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Boolean getHasSubCategory() {
        return hasSubCategory;
    }

    public void setHasSubCategory(Boolean hasSubCategory) {
        this.hasSubCategory = hasSubCategory;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}