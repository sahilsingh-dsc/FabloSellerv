package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MpOrderStatusUpdateRequest {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("reason")
    @Expose
    private String reason;

    /**
     * No args constructor for use in serialization
     */
    public MpOrderStatusUpdateRequest() {
    }

    /**
     * @param jobId
     * @param reason
     * @param marketplaceUserId
     * @param userType
     * @param accessToken
     * @param userId
     * @param status
     */
    public MpOrderStatusUpdateRequest(String accessToken, Integer marketplaceUserId, Integer userType, Integer userId, Integer jobId, Integer status, String reason) {
        super();
        this.accessToken = accessToken;
        this.marketplaceUserId = marketplaceUserId;
        this.userType = userType;
        this.userId = userId;
        this.jobId = jobId;
        this.status = status;
        this.reason = reason;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Integer marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}