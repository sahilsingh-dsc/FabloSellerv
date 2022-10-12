package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRejectOrderRequest {

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
    @SerializedName("accept_reject")
    @Expose
    private Integer acceptReject;

    /**
     * No args constructor for use in serialization
     */
    public AcceptRejectOrderRequest() {
    }

    /**
     * @param jobId
     * @param marketplaceUserId
     * @param apiKey
     * @param acceptReject
     * @param userId
     * @param accessUserType
     */
    public AcceptRejectOrderRequest(String apiKey, Integer accessUserType, Integer jobId, Integer userId, Integer marketplaceUserId, Integer acceptReject) {
        super();
        this.apiKey = apiKey;
        this.accessUserType = accessUserType;
        this.jobId = jobId;
        this.userId = userId;
        this.marketplaceUserId = marketplaceUserId;
        this.acceptReject = acceptReject;
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

    public Integer getAcceptReject() {
        return acceptReject;
    }

    public void setAcceptReject(Integer acceptReject) {
        this.acceptReject = acceptReject;
    }

}