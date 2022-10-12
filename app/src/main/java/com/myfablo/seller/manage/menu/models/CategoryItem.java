package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryItem {

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

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryItem() {
    }

    /**
     *
     * @param categoryImage
     * @param hasSubCategory
     * @param categoryName
     * @param categoryId
     * @param categoryDesc
     */
    public CategoryItem(String categoryId, String categoryName, String categoryDesc, String categoryImage, Boolean hasSubCategory) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryImage = categoryImage;
        this.hasSubCategory = hasSubCategory;
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

}
