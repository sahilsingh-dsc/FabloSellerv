package com.myfablo.seller.catalogue.product.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsByCategoryRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parentCategoryId;

    /**
     * No args constructor for use in serialization
     */
    public ProductsByCategoryRequest() {
    }

    /**
     * @param marketplaceUserId
     * @param apiKey
     * @param offset
     * @param limit
     * @param parentCategoryId
     * @param userId
     */
    public ProductsByCategoryRequest(String apiKey, Integer marketplaceUserId, Integer userId, Integer limit, Integer offset, Integer parentCategoryId) {
        super();
        this.apiKey = apiKey;
        this.marketplaceUserId = marketplaceUserId;
        this.userId = userId;
        this.limit = limit;
        this.offset = offset;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

}