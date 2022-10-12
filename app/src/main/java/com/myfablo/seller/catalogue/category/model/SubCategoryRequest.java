package com.myfablo.seller.catalogue.category.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parentCategoryId;

    /**
     * No args constructor for use in serialization
     */
    public SubCategoryRequest() {
    }

    /**
     * @param marketplaceUserId
     * @param apiKey
     * @param userId
     * @param parentCategoryId
     */

    public SubCategoryRequest(String apiKey, Integer marketplaceUserId, Integer userId, Integer parentCategoryId) {
        this.apiKey = apiKey;
        this.marketplaceUserId = marketplaceUserId;
        this.userId = userId;
        this.parentCategoryId = parentCategoryId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Integer marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

}