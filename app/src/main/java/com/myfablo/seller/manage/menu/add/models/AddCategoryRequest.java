package com.myfablo.seller.manage.menu.add.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCategoryRequest {

    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("parentCategoryId")
    @Expose
    private String parentCategoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    /**
     * No args constructor for use in serialization
     */
    public AddCategoryRequest() {
    }

    /**
     * @param outletId
     * @param parentCategoryId
     * @param categoryName
     */
    public AddCategoryRequest(String outletId, String parentCategoryId, String categoryName) {
        super();
        this.outletId = outletId;
        this.parentCategoryId = parentCategoryId;
        this.categoryName = categoryName;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
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