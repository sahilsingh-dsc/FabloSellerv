package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("prevPage")
    @Expose
    private Integer prevPage;
    @SerializedName("nextPage")
    @Expose
    private Integer nextPage;
    @SerializedName("menu")
    @Expose
    private List<Menu> menu = null;

    /**
     * No args constructor for use in serialization
     */
    public Items() {
    }

    /**
     * @param menu
     * @param itemCount
     */

    public Items(Integer itemCount, Integer totalPage, Integer currentPage, Integer prevPage, Integer nextPage, List<Menu> menu) {
        this.itemCount = itemCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
        this.menu = menu;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}