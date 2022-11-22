package com.myfablo.seller.home.account.insights.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("totalSale")
    @Expose
    private Integer totalSale;
    @SerializedName("orderCount")
    @Expose
    private Integer orderCount;
    @SerializedName("totalDiscount")
    @Expose
    private Integer totalDiscount;
    @SerializedName("netProfit")
    @Expose
    private Integer netProfit;

    /**
     * No args constructor for use in serialization
     *
     */
    public Items() {
    }

    /**
     *
     * @param orderCount
     * @param totalDiscount
     * @param totalSale
     * @param netProfit
     */
    public Items(Integer totalSale, Integer orderCount, Integer totalDiscount, Integer netProfit) {
        super();
        this.totalSale = totalSale;
        this.orderCount = orderCount;
        this.totalDiscount = totalDiscount;
        this.netProfit = netProfit;
    }

    public Integer getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Integer totalSale) {
        this.totalSale = totalSale;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Integer getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Integer netProfit) {
        this.netProfit = netProfit;
    }

}