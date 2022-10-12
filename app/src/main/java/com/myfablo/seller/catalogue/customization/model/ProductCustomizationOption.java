package com.myfablo.seller.catalogue.customization.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCustomizationOption {

    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_default")
    @Expose
    private Integer isDefault;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("sub_product_id")
    @Expose
    private Object subProductId;
    @SerializedName("sub_custom_id")
    @Expose
    private Object subCustomId;
    @SerializedName("sub_cust_id")
    @Expose
    private Object subCustId;
    @SerializedName("addons")
    @Expose
    private List<Object> addons = null;

    public ProductCustomizationOption() {
    }

    public ProductCustomizationOption(Integer custId, String name, Integer isDefault, Integer price, Object subProductId, Object subCustomId, Object subCustId, List<Object> addons) {
        this.custId = custId;
        this.name = name;
        this.isDefault = isDefault;
        this.price = price;
        this.subProductId = subProductId;
        this.subCustomId = subCustomId;
        this.subCustId = subCustId;
        this.addons = addons;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getSubProductId() {
        return subProductId;
    }

    public void setSubProductId(Object subProductId) {
        this.subProductId = subProductId;
    }

    public Object getSubCustomId() {
        return subCustomId;
    }

    public void setSubCustomId(Object subCustomId) {
        this.subCustomId = subCustomId;
    }

    public Object getSubCustId() {
        return subCustId;
    }

    public void setSubCustId(Object subCustId) {
        this.subCustId = subCustId;
    }

    public List<Object> getAddons() {
        return addons;
    }

    public void setAddons(List<Object> addons) {
        this.addons = addons;
    }
}
