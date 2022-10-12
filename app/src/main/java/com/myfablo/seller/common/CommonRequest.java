package com.myfablo.seller.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    /**
     * No args constructor for use in serialization
     */
    public CommonRequest() {
    }

    /**
     * @param marketplaceUserId
     * @param apiKey
     * @param userId
     */
    public CommonRequest(String apiKey, Integer marketplaceUserId, Integer userId) {
        super();
        this.apiKey = apiKey;
        this.marketplaceUserId = marketplaceUserId;
        this.userId = userId;
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

}