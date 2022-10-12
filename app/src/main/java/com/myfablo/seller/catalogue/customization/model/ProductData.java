package com.myfablo.seller.catalogue.customization.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {

    @SerializedName("customization")
    @Expose
    private List<ProductCustomization> customization = null;
    @SerializedName("varaint_sub_addons")
    @Expose
    private List<Object> varaintSubAddons = null;
    @SerializedName("is_variant")
    @Expose
    private Integer isVariant;

    public ProductData() {
    }

    public ProductData(List<ProductCustomization> customization, List<Object> varaintSubAddons, Integer isVariant) {
        this.customization = customization;
        this.varaintSubAddons = varaintSubAddons;
        this.isVariant = isVariant;
    }

    public List<ProductCustomization> getCustomization() {
        return customization;
    }

    public void setCustomization(List<ProductCustomization> customization) {
        this.customization = customization;
    }

    public List<Object> getVaraintSubAddons() {
        return varaintSubAddons;
    }

    public void setVaraintSubAddons(List<Object> varaintSubAddons) {
        this.varaintSubAddons = varaintSubAddons;
    }

    public Integer getIsVariant() {
        return isVariant;
    }

    public void setIsVariant(Integer isVariant) {
        this.isVariant = isVariant;
    }
}
