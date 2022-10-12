package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderStatusChangeRequest {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;

    /**
     * No args constructor for use in serialization
     */
    public OrderStatusChangeRequest() {
    }

    /**
     * @param orderId
     * @param orderStatus
     */
    public OrderStatusChangeRequest(String orderId, String orderStatus) {
        super();
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}