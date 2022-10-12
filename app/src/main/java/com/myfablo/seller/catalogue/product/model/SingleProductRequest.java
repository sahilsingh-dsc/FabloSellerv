package com.myfablo.seller.catalogue.product.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleProductRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("app_type")
    @Expose
    private Integer appType;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    /**
     * No args constructor for use in serialization
     */
    public SingleProductRequest() {
    }

    /**
     * @param marketplaceUserId
     * @param apiKey
     * @param productId
     * @param offset
     * @param appType
     * @param limit
     * @param userId
     */
    public SingleProductRequest(String apiKey, Integer marketplaceUserId, Integer userId, Integer productId, Integer appType, Integer limit, Integer offset) {
        super();
        this.apiKey = apiKey;
        this.marketplaceUserId = marketplaceUserId;
        this.userId = userId;
        this.productId = productId;
        this.appType = appType;
        this.limit = limit;
        this.offset = offset;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
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

}