package com.myfablo.seller.orders.model;

public class OrderItem {

    private String name;
    private String qty;

    public OrderItem() {
    }

    public OrderItem(String name, String qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
