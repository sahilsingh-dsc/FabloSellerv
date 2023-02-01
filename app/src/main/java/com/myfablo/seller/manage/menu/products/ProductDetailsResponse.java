package com.myfablo.seller.manage.menu.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("subcode")
    @Expose
    private Integer subcode;
    @SerializedName("items")
    @Expose
    private Items items;

    /**
     * No args constructor for use in serialization
     */
    public ProductDetailsResponse() {
    }

    /**
     * @param subcode
     * @param items
     * @param status
     */
    public ProductDetailsResponse(Boolean status, Integer subcode, Items items) {
        super();
        this.status = status;
        this.subcode = subcode;
        this.items = items;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSubcode() {
        return subcode;
    }

    public void setSubcode(Integer subcode) {
        this.subcode = subcode;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

}