package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllOrderRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("order_status")
    @Expose
    private Integer orderStatus;
    @SerializedName("sortCol")
    @Expose
    private Integer sortCol;
    @SerializedName("sortDir")
    @Expose
    private String sortDir;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;

    /**
     * No args constructor for use in serialization
     */
    public AllOrderRequest() {
    }

    /**
     * @param sortCol
     * @param marketplaceUserId
     * @param apiKey
     * @param endDate
     * @param start
     * @param length
     * @param orderStatus
     * @param userId
     * @param sortDir
     * @param startDate
     */
    public AllOrderRequest(String apiKey, Integer userId, Integer marketplaceUserId, Integer orderStatus, Integer sortCol, String sortDir, Integer start, Integer length, String startDate, String endDate) {
        super();
        this.apiKey = apiKey;
        this.userId = userId;
        this.marketplaceUserId = marketplaceUserId;
        this.orderStatus = orderStatus;
        this.sortCol = sortCol;
        this.sortDir = sortDir;
        this.start = start;
        this.length = length;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getSortCol() {
        return sortCol;
    }

    public void setSortCol(Integer sortCol) {
        this.sortCol = sortCol;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}