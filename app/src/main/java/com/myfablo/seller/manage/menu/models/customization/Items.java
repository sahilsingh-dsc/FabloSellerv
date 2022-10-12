package com.myfablo.seller.manage.menu.models.customization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfablo.seller.manage.menu.models.Customization;

import java.util.List;

public class Items {

    @SerializedName("customization")
    @Expose
    private List<Customization> customization = null;

    /**
     * No args constructor for use in serialization
     */
    public Items() {
    }

    /**
     * @param customization
     */
    public Items(List<Customization> customization) {
        super();
        this.customization = customization;
    }

    public List<Customization> getCustomization() {
        return customization;
    }

    public void setCustomization(List<Customization> customization) {
        this.customization = customization;
    }

}