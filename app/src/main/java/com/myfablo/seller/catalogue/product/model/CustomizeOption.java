package com.myfablo.seller.catalogue.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomizeOption implements Parcelable {

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

    /**
     * No args constructor for use in serialization
     */
    public CustomizeOption() {
    }

    /**
     * @param isDefault
     * @param addons
     * @param price
     * @param subCustomId
     * @param custId
     * @param name
     * @param subProductId
     * @param subCustId
     */
    public CustomizeOption(Integer custId, String name, Integer isDefault, Integer price, Object subProductId, Object subCustomId, Object subCustId, List<Object> addons) {
        super();
        this.custId = custId;
        this.name = name;
        this.isDefault = isDefault;
        this.price = price;
        this.subProductId = subProductId;
        this.subCustomId = subCustomId;
        this.subCustId = subCustId;
        this.addons = addons;
    }

    protected CustomizeOption(Parcel in) {
        if (in.readByte() == 0) {
            custId = null;
        } else {
            custId = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            isDefault = null;
        } else {
            isDefault = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
    }

    public static final Creator<CustomizeOption> CREATOR = new Creator<CustomizeOption>() {
        @Override
        public CustomizeOption createFromParcel(Parcel in) {
            return new CustomizeOption(in);
        }

        @Override
        public CustomizeOption[] newArray(int size) {
            return new CustomizeOption[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (custId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(custId);
        }
        parcel.writeString(name);
        if (isDefault == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isDefault);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(price);
        }
    }
}