package com.myfablo.seller.manage.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("productList")
    @Expose
    private List<Product> productList = null;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("outlet")
    @Expose
    private Outlet outlet;
    @SerializedName("payableAmount")
    @Expose
    private Float payableAmount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("timing")
    @Expose
    private List<Timing> timing = null;

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param amount
     * @param orderId
     * @param timing
     * @param client
     * @param outlet
     * @param productList
     * @param payableAmount
     * @param status
     */
    public Item(String orderId, List<Product> productList, Client client, Outlet outlet, Float payableAmount, String status, Amount amount, List<Timing> timing) {
        super();
        this.orderId = orderId;
        this.productList = productList;
        this.client = client;
        this.outlet = outlet;
        this.payableAmount = payableAmount;
        this.status = status;
        this.amount = amount;
        this.timing = timing;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Float getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Float payableAmount) {
        this.payableAmount = payableAmount;
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

}