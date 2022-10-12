package com.myfablo.seller.catalogue.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum implements Parcelable {

    @SerializedName("agent_id")
    @Expose
    private Integer agentId;
    @SerializedName("enable_tookan_agent")
    @Expose
    private Integer enableTookanAgent;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parentCategoryId;
    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;
    @SerializedName("inventory_enabled")
    @Expose
    private Integer inventoryEnabled;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("multi_image_url")
    @Expose
    private List<Object> multiImageUrl = null;
    @SerializedName("multi_video_url")
    @Expose
    private List<Object> multiVideoUrl = null;
    @SerializedName("thumb_list")
    @Expose
    private ThumbList thumbList;
    @SerializedName("return_enabled")
    @Expose
    private Integer returnEnabled;
    @SerializedName("minimum_quantity")
    @Expose
    private Integer minimumQuantity;
    @SerializedName("maximum_quantity")
    @Expose
    private Integer maximumQuantity;
    @SerializedName("layout_id")
    @Expose
    private String layoutId;
    @SerializedName("layout_type")
    @Expose
    private Integer layoutType;
    @SerializedName("product_enabled")
    @Expose
    private Integer productEnabled;
    @SerializedName("product_type_id")
    @Expose
    private Object productTypeId;
    @SerializedName("custom_fields")
    @Expose
    private Object customFields;
    @SerializedName("long_description")
    @Expose
    private String longDescription;
    @SerializedName("pt_custom_fields")
    @Expose
    private Object ptCustomFields;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("display_address")
    @Expose
    private Object displayAddress;
    @SerializedName("pricing_id")
    @Expose
    private Integer pricingId;
    @SerializedName("geofence_id")
    @Expose
    private Object geofenceId;
    @SerializedName("form_id")
    @Expose
    private Integer formId;
    @SerializedName("base_unit_id")
    @Expose
    private Integer baseUnitId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("cost_price")
    @Expose
    private Object costPrice;
    @SerializedName("mrp")
    @Expose
    private Integer mrp;
    @SerializedName("is_enabled")
    @Expose
    private Integer isEnabled;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("region_id")
    @Expose
    private Object regionId;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("available_seller_quantity")
    @Expose
    private Integer availableSellerQuantity;
    @SerializedName("unit")
    @Expose
    private Integer unit;
    @SerializedName("unit_type")
    @Expose
    private Integer unitType;
    @SerializedName("marketplace_user_id")
    @Expose
    private Object marketplaceUserId;
    @SerializedName("product_rating")
    @Expose
    private Integer productRating;
    @SerializedName("total_ratings_count")
    @Expose
    private Integer totalRatingsCount;
    @SerializedName("total_review_count")
    @Expose
    private Integer totalReviewCount;
    @SerializedName("total_ratings_sum")
    @Expose
    private Integer totalRatingsSum;
    @SerializedName("last_review_rating")
    @Expose
    private List<Object> lastReviewRating = null;
    @SerializedName("is_product_review_rating_enable")
    @Expose
    private Integer isProductReviewRatingEnable;
    @SerializedName("update_datetime")
    @Expose
    private String updateDatetime;
    @SerializedName("is_tookan_active")
    @Expose
    private Integer isTookanActive;
    @SerializedName("is_recurring_enabled")
    @Expose
    private Integer isRecurringEnabled;
    @SerializedName("is_agents_on_product_tags_enabled")
    @Expose
    private Integer isAgentsOnProductTagsEnabled;
    @SerializedName("is_product_template_enabled")
    @Expose
    private Integer isProductTemplateEnabled;
    @SerializedName("is_veg")
    @Expose
    private Integer isVeg;
    @SerializedName("is_combo")
    @Expose
    private Integer isCombo;
    @SerializedName("price_on_selection")
    @Expose
    private Integer priceOnSelection;
    @SerializedName("gif_url")
    @Expose
    private List<Object> gifUrl = null;
    @SerializedName("unit_in_text")
    @Expose
    private String unitInText;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("is_static_address_enabled")
    @Expose
    private Integer isStaticAddressEnabled;
    @SerializedName("delivery_by_merchant")
    @Expose
    private Integer deliveryByMerchant;
    @SerializedName("is_menu_enabled")
    @Expose
    private Integer isMenuEnabled;
    @SerializedName("date_time")
    @Expose
    private Object dateTime;
    @SerializedName("is_preorder_selected_for_menu")
    @Expose
    private Integer isPreorderSelectedForMenu;
    @SerializedName("often_bought_products")
    @Expose
    private List<Object> oftenBoughtProducts = null;
    @SerializedName("has_customizations")
    @Expose
    private Integer hasCustomizations;
    @SerializedName("customization")
    @Expose
    private List<Customization> customization = null;
    @SerializedName("varaint_sub_addons")
    @Expose
    private List<Object> varaintSubAddons = null;
    @SerializedName("is_variant")
    @Expose
    private Integer isVariant;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param multiImageUrl
     * @param multiVideoUrl
     * @param isAgentsOnProductTagsEnabled
     * @param discount
     * @param isCombo
     * @param totalReviewCount
     * @param pricingId
     * @param isStaticAddressEnabled
     * @param sellerId
     * @param price
     * @param availableSellerQuantity
     * @param totalRatingsSum
     * @param productRating
     * @param isRecurringEnabled
     * @param longitude
     * @param formId
     * @param baseUnitId
     * @param updateDatetime
     * @param productId
     * @param enableTookanAgent
     * @param thumbList
     * @param oftenBoughtProducts
     * @param gifUrl
     * @param unit
     * @param displayAddress
     * @param isProductTemplateEnabled
     * @param isEnabled
     * @param name
     * @param geofenceId
     * @param isMenuEnabled
     * @param longDescription
     * @param dateTime
     * @param agentId
     * @param inventoryEnabled
     * @param customization
     * @param customFields
     * @param productEnabled
     * @param latitude
     * @param description
     * @param unitType
     * @param ptCustomFields
     * @param returnEnabled
     * @param marketplaceUserId
     * @param isDeleted
     * @param imageUrl
     * @param layoutType
     * @param lastReviewRating
     * @param minimumQuantity
     * @param maximumQuantity
     * @param availableQuantity
     * @param address
     * @param hasCustomizations
     * @param costPrice
     * @param priceOnSelection
     * @param mrp
     * @param userId
     * @param layoutId
     * @param productTypeId
     * @param isVeg
     * @param regionId
     * @param isVariant
     * @param varaintSubAddons
     * @param unitInText
     * @param isTookanActive
     * @param isPreorderSelectedForMenu
     * @param parentCategoryId
     * @param isProductReviewRatingEnable
     * @param totalRatingsCount
     * @param deliveryByMerchant
     */
    public Datum(Integer agentId, Integer enableTookanAgent, Integer productId, String name, String description, Integer parentCategoryId, Integer availableQuantity, Integer inventoryEnabled, Integer userId, String imageUrl, List<Object> multiImageUrl, List<Object> multiVideoUrl, ThumbList thumbList, Integer returnEnabled, Integer minimumQuantity, Integer maximumQuantity, String layoutId, Integer layoutType, Integer productEnabled, Object productTypeId, Object customFields, String longDescription, Object ptCustomFields, Object address, Object latitude, Object longitude, Object displayAddress, Integer pricingId, Object geofenceId, Integer formId, Integer baseUnitId, Integer price, Object costPrice, Integer mrp, Integer isEnabled, Integer isDeleted, Object regionId, Integer sellerId, Integer availableSellerQuantity, Integer unit, Integer unitType, Object marketplaceUserId, Integer productRating, Integer totalRatingsCount, Integer totalReviewCount, Integer totalRatingsSum, List<Object> lastReviewRating, Integer isProductReviewRatingEnable, String updateDatetime, Integer isTookanActive, Integer isRecurringEnabled, Integer isAgentsOnProductTagsEnabled, Integer isProductTemplateEnabled, Integer isVeg, Integer isCombo, Integer priceOnSelection, List<Object> gifUrl, String unitInText, Integer discount, Integer isStaticAddressEnabled, Integer deliveryByMerchant, Integer isMenuEnabled, Object dateTime, Integer isPreorderSelectedForMenu, List<Object> oftenBoughtProducts, Integer hasCustomizations, List<Customization> customization, List<Object> varaintSubAddons, Integer isVariant) {
        super();
        this.agentId = agentId;
        this.enableTookanAgent = enableTookanAgent;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
        this.availableQuantity = availableQuantity;
        this.inventoryEnabled = inventoryEnabled;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.multiImageUrl = multiImageUrl;
        this.multiVideoUrl = multiVideoUrl;
        this.thumbList = thumbList;
        this.returnEnabled = returnEnabled;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
        this.layoutId = layoutId;
        this.layoutType = layoutType;
        this.productEnabled = productEnabled;
        this.productTypeId = productTypeId;
        this.customFields = customFields;
        this.longDescription = longDescription;
        this.ptCustomFields = ptCustomFields;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.displayAddress = displayAddress;
        this.pricingId = pricingId;
        this.geofenceId = geofenceId;
        this.formId = formId;
        this.baseUnitId = baseUnitId;
        this.price = price;
        this.costPrice = costPrice;
        this.mrp = mrp;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
        this.regionId = regionId;
        this.sellerId = sellerId;
        this.availableSellerQuantity = availableSellerQuantity;
        this.unit = unit;
        this.unitType = unitType;
        this.marketplaceUserId = marketplaceUserId;
        this.productRating = productRating;
        this.totalRatingsCount = totalRatingsCount;
        this.totalReviewCount = totalReviewCount;
        this.totalRatingsSum = totalRatingsSum;
        this.lastReviewRating = lastReviewRating;
        this.isProductReviewRatingEnable = isProductReviewRatingEnable;
        this.updateDatetime = updateDatetime;
        this.isTookanActive = isTookanActive;
        this.isRecurringEnabled = isRecurringEnabled;
        this.isAgentsOnProductTagsEnabled = isAgentsOnProductTagsEnabled;
        this.isProductTemplateEnabled = isProductTemplateEnabled;
        this.isVeg = isVeg;
        this.isCombo = isCombo;
        this.priceOnSelection = priceOnSelection;
        this.gifUrl = gifUrl;
        this.unitInText = unitInText;
        this.discount = discount;
        this.isStaticAddressEnabled = isStaticAddressEnabled;
        this.deliveryByMerchant = deliveryByMerchant;
        this.isMenuEnabled = isMenuEnabled;
        this.dateTime = dateTime;
        this.isPreorderSelectedForMenu = isPreorderSelectedForMenu;
        this.oftenBoughtProducts = oftenBoughtProducts;
        this.hasCustomizations = hasCustomizations;
        this.customization = customization;
        this.varaintSubAddons = varaintSubAddons;
        this.isVariant = isVariant;
    }

    protected Datum(Parcel in) {
        if (in.readByte() == 0) {
            agentId = null;
        } else {
            agentId = in.readInt();
        }
        if (in.readByte() == 0) {
            enableTookanAgent = null;
        } else {
            enableTookanAgent = in.readInt();
        }
        if (in.readByte() == 0) {
            productId = null;
        } else {
            productId = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            parentCategoryId = null;
        } else {
            parentCategoryId = in.readInt();
        }
        if (in.readByte() == 0) {
            availableQuantity = null;
        } else {
            availableQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            inventoryEnabled = null;
        } else {
            inventoryEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            returnEnabled = null;
        } else {
            returnEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            minimumQuantity = null;
        } else {
            minimumQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            maximumQuantity = null;
        } else {
            maximumQuantity = in.readInt();
        }
        layoutId = in.readString();
        if (in.readByte() == 0) {
            layoutType = null;
        } else {
            layoutType = in.readInt();
        }
        if (in.readByte() == 0) {
            productEnabled = null;
        } else {
            productEnabled = in.readInt();
        }
        longDescription = in.readString();
        if (in.readByte() == 0) {
            pricingId = null;
        } else {
            pricingId = in.readInt();
        }
        if (in.readByte() == 0) {
            formId = null;
        } else {
            formId = in.readInt();
        }
        if (in.readByte() == 0) {
            baseUnitId = null;
        } else {
            baseUnitId = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        if (in.readByte() == 0) {
            mrp = null;
        } else {
            mrp = in.readInt();
        }
        if (in.readByte() == 0) {
            isEnabled = null;
        } else {
            isEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            isDeleted = null;
        } else {
            isDeleted = in.readInt();
        }
        if (in.readByte() == 0) {
            sellerId = null;
        } else {
            sellerId = in.readInt();
        }
        if (in.readByte() == 0) {
            availableSellerQuantity = null;
        } else {
            availableSellerQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            unit = null;
        } else {
            unit = in.readInt();
        }
        if (in.readByte() == 0) {
            unitType = null;
        } else {
            unitType = in.readInt();
        }
        if (in.readByte() == 0) {
            productRating = null;
        } else {
            productRating = in.readInt();
        }
        if (in.readByte() == 0) {
            totalRatingsCount = null;
        } else {
            totalRatingsCount = in.readInt();
        }
        if (in.readByte() == 0) {
            totalReviewCount = null;
        } else {
            totalReviewCount = in.readInt();
        }
        if (in.readByte() == 0) {
            totalRatingsSum = null;
        } else {
            totalRatingsSum = in.readInt();
        }
        if (in.readByte() == 0) {
            isProductReviewRatingEnable = null;
        } else {
            isProductReviewRatingEnable = in.readInt();
        }
        updateDatetime = in.readString();
        if (in.readByte() == 0) {
            isTookanActive = null;
        } else {
            isTookanActive = in.readInt();
        }
        if (in.readByte() == 0) {
            isRecurringEnabled = null;
        } else {
            isRecurringEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            isAgentsOnProductTagsEnabled = null;
        } else {
            isAgentsOnProductTagsEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            isProductTemplateEnabled = null;
        } else {
            isProductTemplateEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            isVeg = null;
        } else {
            isVeg = in.readInt();
        }
        if (in.readByte() == 0) {
            isCombo = null;
        } else {
            isCombo = in.readInt();
        }
        if (in.readByte() == 0) {
            priceOnSelection = null;
        } else {
            priceOnSelection = in.readInt();
        }
        unitInText = in.readString();
        if (in.readByte() == 0) {
            discount = null;
        } else {
            discount = in.readInt();
        }
        if (in.readByte() == 0) {
            isStaticAddressEnabled = null;
        } else {
            isStaticAddressEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            deliveryByMerchant = null;
        } else {
            deliveryByMerchant = in.readInt();
        }
        if (in.readByte() == 0) {
            isMenuEnabled = null;
        } else {
            isMenuEnabled = in.readInt();
        }
        if (in.readByte() == 0) {
            isPreorderSelectedForMenu = null;
        } else {
            isPreorderSelectedForMenu = in.readInt();
        }
        if (in.readByte() == 0) {
            hasCustomizations = null;
        } else {
            hasCustomizations = in.readInt();
        }
        if (in.readByte() == 0) {
            isVariant = null;
        } else {
            isVariant = in.readInt();
        }
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getEnableTookanAgent() {
        return enableTookanAgent;
    }

    public void setEnableTookanAgent(Integer enableTookanAgent) {
        this.enableTookanAgent = enableTookanAgent;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getInventoryEnabled() {
        return inventoryEnabled;
    }

    public void setInventoryEnabled(Integer inventoryEnabled) {
        this.inventoryEnabled = inventoryEnabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Object> getMultiImageUrl() {
        return multiImageUrl;
    }

    public void setMultiImageUrl(List<Object> multiImageUrl) {
        this.multiImageUrl = multiImageUrl;
    }

    public List<Object> getMultiVideoUrl() {
        return multiVideoUrl;
    }

    public void setMultiVideoUrl(List<Object> multiVideoUrl) {
        this.multiVideoUrl = multiVideoUrl;
    }

    public ThumbList getThumbList() {
        return thumbList;
    }

    public void setThumbList(ThumbList thumbList) {
        this.thumbList = thumbList;
    }

    public Integer getReturnEnabled() {
        return returnEnabled;
    }

    public void setReturnEnabled(Integer returnEnabled) {
        this.returnEnabled = returnEnabled;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Integer getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(Integer maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public Integer getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(Integer layoutType) {
        this.layoutType = layoutType;
    }

    public Integer getProductEnabled() {
        return productEnabled;
    }

    public void setProductEnabled(Integer productEnabled) {
        this.productEnabled = productEnabled;
    }

    public Object getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Object productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Object getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Object customFields) {
        this.customFields = customFields;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Object getPtCustomFields() {
        return ptCustomFields;
    }

    public void setPtCustomFields(Object ptCustomFields) {
        this.ptCustomFields = ptCustomFields;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(Object displayAddress) {
        this.displayAddress = displayAddress;
    }

    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    public Object getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(Object geofenceId) {
        this.geofenceId = geofenceId;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getBaseUnitId() {
        return baseUnitId;
    }

    public void setBaseUnitId(Integer baseUnitId) {
        this.baseUnitId = baseUnitId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Object costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Object getRegionId() {
        return regionId;
    }

    public void setRegionId(Object regionId) {
        this.regionId = regionId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getAvailableSellerQuantity() {
        return availableSellerQuantity;
    }

    public void setAvailableSellerQuantity(Integer availableSellerQuantity) {
        this.availableSellerQuantity = availableSellerQuantity;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Object getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Object marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getProductRating() {
        return productRating;
    }

    public void setProductRating(Integer productRating) {
        this.productRating = productRating;
    }

    public Integer getTotalRatingsCount() {
        return totalRatingsCount;
    }

    public void setTotalRatingsCount(Integer totalRatingsCount) {
        this.totalRatingsCount = totalRatingsCount;
    }

    public Integer getTotalReviewCount() {
        return totalReviewCount;
    }

    public void setTotalReviewCount(Integer totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }

    public Integer getTotalRatingsSum() {
        return totalRatingsSum;
    }

    public void setTotalRatingsSum(Integer totalRatingsSum) {
        this.totalRatingsSum = totalRatingsSum;
    }

    public List<Object> getLastReviewRating() {
        return lastReviewRating;
    }

    public void setLastReviewRating(List<Object> lastReviewRating) {
        this.lastReviewRating = lastReviewRating;
    }

    public Integer getIsProductReviewRatingEnable() {
        return isProductReviewRatingEnable;
    }

    public void setIsProductReviewRatingEnable(Integer isProductReviewRatingEnable) {
        this.isProductReviewRatingEnable = isProductReviewRatingEnable;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getIsTookanActive() {
        return isTookanActive;
    }

    public void setIsTookanActive(Integer isTookanActive) {
        this.isTookanActive = isTookanActive;
    }

    public Integer getIsRecurringEnabled() {
        return isRecurringEnabled;
    }

    public void setIsRecurringEnabled(Integer isRecurringEnabled) {
        this.isRecurringEnabled = isRecurringEnabled;
    }

    public Integer getIsAgentsOnProductTagsEnabled() {
        return isAgentsOnProductTagsEnabled;
    }

    public void setIsAgentsOnProductTagsEnabled(Integer isAgentsOnProductTagsEnabled) {
        this.isAgentsOnProductTagsEnabled = isAgentsOnProductTagsEnabled;
    }

    public Integer getIsProductTemplateEnabled() {
        return isProductTemplateEnabled;
    }

    public void setIsProductTemplateEnabled(Integer isProductTemplateEnabled) {
        this.isProductTemplateEnabled = isProductTemplateEnabled;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public Integer getIsCombo() {
        return isCombo;
    }

    public void setIsCombo(Integer isCombo) {
        this.isCombo = isCombo;
    }

    public Integer getPriceOnSelection() {
        return priceOnSelection;
    }

    public void setPriceOnSelection(Integer priceOnSelection) {
        this.priceOnSelection = priceOnSelection;
    }

    public List<Object> getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(List<Object> gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getUnitInText() {
        return unitInText;
    }

    public void setUnitInText(String unitInText) {
        this.unitInText = unitInText;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getIsStaticAddressEnabled() {
        return isStaticAddressEnabled;
    }

    public void setIsStaticAddressEnabled(Integer isStaticAddressEnabled) {
        this.isStaticAddressEnabled = isStaticAddressEnabled;
    }

    public Integer getDeliveryByMerchant() {
        return deliveryByMerchant;
    }

    public void setDeliveryByMerchant(Integer deliveryByMerchant) {
        this.deliveryByMerchant = deliveryByMerchant;
    }

    public Integer getIsMenuEnabled() {
        return isMenuEnabled;
    }

    public void setIsMenuEnabled(Integer isMenuEnabled) {
        this.isMenuEnabled = isMenuEnabled;
    }

    public Object getDateTime() {
        return dateTime;
    }

    public void setDateTime(Object dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getIsPreorderSelectedForMenu() {
        return isPreorderSelectedForMenu;
    }

    public void setIsPreorderSelectedForMenu(Integer isPreorderSelectedForMenu) {
        this.isPreorderSelectedForMenu = isPreorderSelectedForMenu;
    }

    public List<Object> getOftenBoughtProducts() {
        return oftenBoughtProducts;
    }

    public void setOftenBoughtProducts(List<Object> oftenBoughtProducts) {
        this.oftenBoughtProducts = oftenBoughtProducts;
    }

    public Integer getHasCustomizations() {
        return hasCustomizations;
    }

    public void setHasCustomizations(Integer hasCustomizations) {
        this.hasCustomizations = hasCustomizations;
    }

    public List<Customization> getCustomization() {
        return customization;
    }

    public void setCustomization(List<Customization> customization) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (agentId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(agentId);
        }
        if (enableTookanAgent == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(enableTookanAgent);
        }
        if (productId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productId);
        }
        parcel.writeString(name);
        parcel.writeString(description);
        if (parentCategoryId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(parentCategoryId);
        }
        if (availableQuantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(availableQuantity);
        }
        if (inventoryEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(inventoryEnabled);
        }
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(imageUrl);
        if (returnEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(returnEnabled);
        }
        if (minimumQuantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(minimumQuantity);
        }
        if (maximumQuantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maximumQuantity);
        }
        parcel.writeString(layoutId);
        if (layoutType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(layoutType);
        }
        if (productEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productEnabled);
        }
        parcel.writeString(longDescription);
        if (pricingId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(pricingId);
        }
        if (formId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(formId);
        }
        if (baseUnitId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(baseUnitId);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(price);
        }
        if (mrp == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mrp);
        }
        if (isEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isEnabled);
        }
        if (isDeleted == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isDeleted);
        }
        if (sellerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(sellerId);
        }
        if (availableSellerQuantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(availableSellerQuantity);
        }
        if (unit == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(unit);
        }
        if (unitType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(unitType);
        }
        if (productRating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productRating);
        }
        if (totalRatingsCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalRatingsCount);
        }
        if (totalReviewCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalReviewCount);
        }
        if (totalRatingsSum == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(totalRatingsSum);
        }
        if (isProductReviewRatingEnable == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isProductReviewRatingEnable);
        }
        parcel.writeString(updateDatetime);
        if (isTookanActive == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isTookanActive);
        }
        if (isRecurringEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isRecurringEnabled);
        }
        if (isAgentsOnProductTagsEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isAgentsOnProductTagsEnabled);
        }
        if (isProductTemplateEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isProductTemplateEnabled);
        }
        if (isVeg == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isVeg);
        }
        if (isCombo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isCombo);
        }
        if (priceOnSelection == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(priceOnSelection);
        }
        parcel.writeString(unitInText);
        if (discount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(discount);
        }
        if (isStaticAddressEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isStaticAddressEnabled);
        }
        if (deliveryByMerchant == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(deliveryByMerchant);
        }
        if (isMenuEnabled == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isMenuEnabled);
        }
        if (isPreorderSelectedForMenu == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isPreorderSelectedForMenu);
        }
        if (hasCustomizations == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(hasCustomizations);
        }
        if (isVariant == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isVariant);
        }
    }
}