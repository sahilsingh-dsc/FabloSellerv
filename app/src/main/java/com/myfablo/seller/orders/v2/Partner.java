package com.myfablo.seller.orders.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partner {

    @SerializedName("_id")
    @Expose
    private String id;

    /**
     * No args constructor for use in serialization
     */
    public Partner() {
    }

    /**
     * @param id
     */
    public Partner(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}