package com.myfablo.seller.manage.menu.models;

public class StockUpdate {
    
    private String productId;

    public StockUpdate() {
    }

    public StockUpdate(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
