package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
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
    public Items(Integer itemCount, List<Menu> menu) {
        super();
        this.itemCount = itemCount;
        this.menu = menu;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

}