package com.myfablo.seller.manage.menu.add.subcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSubCategoryRequest {

    @SerializedName("parentCategoryId")
    @Expose
    private String parentCategoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    /**
     * No args constructor for use in serialization
     */
    public AddSubCategoryRequest() {
    }

    /**
     * @param parentCategoryId
     * @param categoryName
     */
    public AddSubCategoryRequest(String parentCategoryId, String categoryName) {
        super();
        this.parentCategoryId = parentCategoryId;
        this.categoryName = categoryName;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}