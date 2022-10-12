package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOrderStatusRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("access_user_type")
    @Expose
    private Integer accessUserType;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     */
    public UpdateOrderStatusRequest() {
    }

    /**
     * @param jobId
     * @param marketplaceUserId
     * @param apiKey
     * @param status
     * @param userId
     * @param accessUserType
     */

    public UpdateOrderStatusRequest(String apiKey, Integer accessUserType, Integer jobId, Integer userId, Integer marketplaceUserId, Integer status) {
        this.apiKey = apiKey;
        this.accessUserType = accessUserType;
        this.jobId = jobId;
        this.userId = userId;
        this.marketplaceUserId = marketplaceUserId;
        this.status = status;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getAccessUserType() {
        return accessUserType;
    }

    public void setAccessUserType(Integer accessUserType) {
        this.accessUserType = accessUserType;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Integer marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}