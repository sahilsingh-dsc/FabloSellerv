package com.myfablo.seller.orders.model.order_get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("mpOrderId")
    @Expose
    private String mpOrderId;
    @SerializedName("productList")
    @Expose
    private List<Product> productList;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("outlet")
    @Expose
    private Outlet outlet;
    @SerializedName("payableAmount")
    @Expose
    private Integer payableAmount;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("timing")
    @Expose
    private List<Timing> timing;
    @SerializedName("payment")
    @Expose
    private Payment payment;

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param amount
     * @param distance
     * @param orderId
     * @param timing
     * @param client
     * @param mpOrderId
     * @param payment
     * @param outlet
     * @param productList
     * @param payableAmount
     * @param status
     */
    public Item(String orderId, String mpOrderId, List<Product> productList, Client client, Outlet outlet, Integer payableAmount, String distance, String status, Amount amount, List<Timing> timing, Payment payment) {
        super();
        this.orderId = orderId;
        this.mpOrderId = mpOrderId;
        this.productList = productList;
        this.client = client;
        this.outlet = outlet;
        this.payableAmount = payableAmount;
        this.distance = distance;
        this.status = status;
        this.amount = amount;
        this.timing = timing;
        this.payment = payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMpOrderId() {
        return mpOrderId;
    }

    public void setMpOrderId(String mpOrderId) {
        this.mpOrderId = mpOrderId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    public Integer getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Integer payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public List<Timing> getTiming() {
        return timing;
    }

    public void setTiming(List<Timing> timing) {
        this.timing = timing;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}