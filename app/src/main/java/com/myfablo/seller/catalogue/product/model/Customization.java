package com.myfablo.seller.catalogue.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customization implements Parcelable {

    @SerializedName("customize_id")
    @Expose
    private Integer customizeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_check_box")
    @Expose
    private Integer isCheckBox;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("minimum_selection")
    @Expose
    private Integer minimumSelection;
    @SerializedName("minimum_selection_required")
    @Expose
    private Integer minimumSelectionRequired;
    @SerializedName("customize_options")
    @Expose
    private List<CustomizeOption> customizeOptions = null;
    @SerializedName("is_variant")
    @Expose
    private Integer isVariant;
    @SerializedName("parent_customize_id")
    @Expose
    private Object parentCustomizeId;
    @SerializedName("parent_cust_id")
    @Expose
    private Object parentCustId;
    @SerializedName("mapping_enabled")
    @Expose
    private Integer mappingEnabled;

    /**
     * No args constructor for use in serialization
     */
    public Customization() {
    }

    /**
     * @param mappingEnabled
     * @param minimumSelectionRequired
     * @param customizeId
     * @param customizeOptions
     * @param minimumSelection
     * @param isVariant
     * @param name
     * @param parentCustomizeId
     * @param parentCustId
     * @param type
     * @param isCheckBox
     */
    public Customization(Integer customizeId, String name, Integer isCheckBox, String type, Integer minimumSelection, Integer minimumSelectionRequired, List<CustomizeOption> customizeOptions, Integer isVariant, Object parentCustomizeId, Object parentCustId, Integer mappingEnabled) {
        super();
        this.customizeId = customizeId;
        this.name = name;
        this.isCheckBox = isCheckBox;
        this.type = type;
        this.minimumSelection = minimumSelection;
        this.minimumSelectionRequired = minimumSelectionRequired;
        this.customizeOptions = customizeOptions;
        this.isVariant = isVariant;
        this.parentCustomizeId = parentCustomizeId;
        this.parentCustId = parentCustId;
        this.mappingEnabled = mappingEnabled;
    }

    protected Customization(Parcel in) {
        if (in.readByte() == 0) {
            customizeId = null;
        } else {
            customizeId = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            isCheckBox = null;
        } else {
            isCheckBox = in.readInt();
        }
        type = in.readString();
        if (in.readByte() == 0) {
            minimumSelection = null;
        } else {
            minimumSelection = in.readInt();
        }
        if (in.readByte() == 0) {
            minimumSelectionRequired = null;
        } else {
            minimumSelectionRequired = in.readInt();
        }
        if (in.readByte() == 0) {
            isVariant = null;
        } else {
            isVariant = in.readInt();
        }
        if (in.readByte() == 0) {
            mappingEnabled = null;
        } else {
            mappingEnabled = in.readInt();
        }
    }

    public static final Creator<Customization> CREATOR = new Creator<Customization>() {
        @Override
        public Customization createFromParcel(Parcel in) {
            return new Customization(in);
        }

        @Override
        public Customization[] newArray(int size) {
            return new Customization[size];
        }
    };

    public Integer getCustomizeId() {
        return customizeId;
    }

    public void setCustomizeId(Integer customizeId) {
        this.customizeId = customizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsCheckBox() {
        return isCheckBox;
    }

    public void setIsCheckBox(Integer isCheckBox) {
        this.isCheckBox = isCheckBox;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMinimumSelection() {
        return minimumSelection;
    }

    public void setMinimumSelection(Integer minimumSelection) {
        this.minimumSelection = minimumSelection;
    }

    public Integer getMinimumSelectionRequired() {
        return minimumSelectionRequired;
    }

    public void setMinimumSelectionRequired(Integer minimumSelectionRequired) {
        this.minimumSelectionRequired = minimumSelectionRequired;
    }

    public List<CustomizeOption> getCustomizeOptions() {
        return customizeOptions;
    }

    public void setCustomizeOptions(List<CustomizeOption> customizeOptions) {
        this.customizeOptions = customizeOptions;
    }

    public Integer getIsVariant() {
        return isVariant;
    }

    public void setIsVariant(Integer isVariant) {
        this.isVariant = isVariant;
    }

    public Object getParentCustomizeId() {
        return parentCustomizeId;
    }

    public void setParentCustomizeId(Object parentCustomizeId) {
        this.parentCustomizeId = parentCustomizeId;
    }

    public Object getParentCustId() {
        return parentCustId;
    }

    public void setParentCustId(Object parentCustId) {
        this.parentCustId = parentCustId;
    }

    public Integer getMappingEnabled() {
        return mappingEnabled;
    }

    public void setMappingEnabled(Integer mappingEnabled) {
        this.mappingEnabled = mappingEnabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (customizeId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(customizeId);
        }
        parcel.writeString(name);
        if (isCheckBox == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isCheckBox);
        }
        parcel.writeString(type);
        if (minimumSelection == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(minimumSelection);
        }
        if (minimumSelectionRequired == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(minimumSelectionRequired);
        }
        if (isVariant == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isVariant);
        }
        if (mappingEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mappingEnabled);
        }
    }
}