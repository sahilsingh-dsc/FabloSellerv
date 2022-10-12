package com.myfablo.seller.catalogue.category.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result implements Parcelable {

    @SerializedName("catalogue_id")
    @Expose
    private Integer catalogueId;
    @SerializedName("parent_category_id")
    @Expose
    private Object parentCategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("thumb_list")
    @Expose
    private Object thumbList;
    @SerializedName("has_products")
    @Expose
    private Integer hasProducts;
    @SerializedName("is_side_order")
    @Expose
    private Integer isSideOrder;
    @SerializedName("has_children")
    @Expose
    private Integer hasChildren;
    @SerializedName("is_enabled")
    @Expose
    private Integer isEnabled;
    @SerializedName("layout_id")
    @Expose
    private String layoutId;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("is_required")
    @Expose
    private Integer isRequired;
    @SerializedName("discount_name")
    @Expose
    private Object discountName;
    @SerializedName("discount_id")
    @Expose
    private Object discountId;
    @SerializedName("taxes")
    @Expose
    private List<Object> taxes = null;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param isSideOrder
     * @param isRequired
     * @param catalogueId
     * @param hasChildren
     * @param description
     * @param taxes
     * @param thumbList
     * @param priority
     * @param layoutId
     * @param discountName
     * @param imageUrl
     * @param isEnabled
     * @param name
     * @param parentCategoryId
     * @param thumbUrl
     * @param discountId
     * @param hasProducts
     */
    public Result(Integer catalogueId, Object parentCategoryId, String name, String description, String imageUrl, String thumbUrl, Object thumbList, Integer hasProducts, Integer isSideOrder, Integer hasChildren, Integer isEnabled, String layoutId, Integer priority, Integer isRequired, Object discountName, Object discountId, List<Object> taxes) {
        super();
        this.catalogueId = catalogueId;
        this.parentCategoryId = parentCategoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
        this.thumbList = thumbList;
        this.hasProducts = hasProducts;
        this.isSideOrder = isSideOrder;
        this.hasChildren = hasChildren;
        this.isEnabled = isEnabled;
        this.layoutId = layoutId;
        this.priority = priority;
        this.isRequired = isRequired;
        this.discountName = discountName;
        this.discountId = discountId;
        this.taxes = taxes;
    }

    protected Result(Parcel in) {
        if (in.readByte() == 0) {
            catalogueId = null;
        } else {
            catalogueId = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        thumbUrl = in.readString();
        if (in.readByte() == 0) {
            hasProducts = null;
        } else {
            hasProducts = in.readInt();
        }
        if (in.readByte() == 0) {
            isSideOrder = null;
        } else {
            isSideOrder = in.readInt();
        }
        if (in.readByte() == 0) {
            hasChildren = null;
        } else {
            hasChildren = in.readInt();
        }
        if (in.readByte() == 0) {
            isEnabled = null;
        } else {
            isEnabled = in.readInt();
        }
        layoutId = in.readString();
        if (in.readByte() == 0) {
            priority = null;
        } else {
            priority = in.readInt();
        }
        if (in.readByte() == 0) {
            isRequired = null;
        } else {
            isRequired = in.readInt();
        }
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Integer getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Integer catalogueId) {
        this.catalogueId = catalogueId;
    }

    public Object getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Object parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Object getThumbList() {
        return thumbList;
    }

    public void setThumbList(Object thumbList) {
        this.thumbList = thumbList;
    }

    public Integer getHasProducts() {
        return hasProducts;
    }

    public void setHasProducts(Integer hasProducts) {
        this.hasProducts = hasProducts;
    }

    public Integer getIsSideOrder() {
        return isSideOrder;
    }

    public void setIsSideOrder(Integer isSideOrder) {
        this.isSideOrder = isSideOrder;
    }

    public Integer getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Integer hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public Object getDiscountName() {
        return discountName;
    }

    public void setDiscountName(Object discountName) {
        this.discountName = discountName;
    }

    public Object getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Object discountId) {
        this.discountId = discountId;
    }

    public List<Object> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Object> taxes) {
        this.taxes = taxes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (catalogueId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(catalogueId);
        }
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeString(thumbUrl);
        if (hasProducts == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(hasProducts);
        }
        if (isSideOrder == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isSideOrder);
        }
        if (hasChildren == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(hasChildren);
        }
        if (isEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isEnabled);
        }
        parcel.writeString(layoutId);
        if (priority == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(priority);
        }
        if (isRequired == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isRequired);
        }
    }
}