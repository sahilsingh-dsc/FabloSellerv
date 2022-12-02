package com.myfablo.seller.home.outlets.models.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutletDetailsItem {

    @SerializedName("location")
    @Expose
    private LocationPoint location;
    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("outletName")
    @Expose
    private String outletName;
    @SerializedName("outletImage")
    @Expose
    private List<String> outletImage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("preparationTime")
    @Expose
    private String preparationTime;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("isPureVeg")
    @Expose
    private Boolean isPureVeg;
    @SerializedName("isDiscounted")
    @Expose
    private Boolean isDiscounted;
    @SerializedName("discountArray")
    @Expose
    private List<String> discountArray = null;
    @SerializedName("tag")
    @Expose
    private List<String> tag = null;
    @SerializedName("isFood")
    @Expose
    private Boolean isFood;
    @SerializedName("cuisine")
    @Expose
    private List<String> cuisine = null;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;
    @SerializedName("sellerId")
    @Expose
    private String sellerId;
    @SerializedName("subSellerId")
    @Expose
    private List<Object> subSellerId = null;
    @SerializedName("openingHours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("isClosed")
    @Expose
    private Boolean isClosed;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("isVisible")
    @Expose
    private Boolean isVisible;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("isFeatured")
    @Expose
    private Boolean isFeatured;

    /**
     * No args constructor for use in serialization
     */
    public OutletDetailsItem() {
    }

    /**
     * @param area
     * @param isDiscounted
     * @param isVerified
     * @param outletId
     * @param preparationTime
     * @param rating
     * @param cuisine
     * @param isFood
     * @param isVisible
     * @param type
     * @param shopAddress
     * @param outletName
     * @param isPureVeg
     * @param sellerId
     * @param outletImage
     * @param isClosed
     * @param discountArray
     * @param subSellerId
     * @param location
     * @param openingHours
     * @param tag
     * @param isFeatured
     */
    public OutletDetailsItem(LocationPoint location, String outletId, String outletName, List<String> outletImage, String type, String preparationTime, String area, Boolean isPureVeg, Boolean isDiscounted, List<String> discountArray, List<String> tag, Boolean isFood, List<String> cuisine, String shopAddress, String sellerId, List<Object> subSellerId, OpeningHours openingHours, Boolean isClosed, Boolean isVerified, Boolean isVisible, Object rating, Boolean isFeatured) {
        super();
        this.location = location;
        this.outletId = outletId;
        this.outletName = outletName;
        this.outletImage = outletImage;
        this.type = type;
        this.preparationTime = preparationTime;
        this.area = area;
        this.isPureVeg = isPureVeg;
        this.isDiscounted = isDiscounted;
        this.discountArray = discountArray;
        this.tag = tag;
        this.isFood = isFood;
        this.cuisine = cuisine;
        this.shopAddress = shopAddress;
        this.sellerId = sellerId;
        this.subSellerId = subSellerId;
        this.openingHours = openingHours;
        this.isClosed = isClosed;
        this.isVerified = isVerified;
        this.isVisible = isVisible;
        this.rating = rating;
        this.isFeatured = isFeatured;
    }

    public LocationPoint getLocation() {
        return location;
    }

    public void setLocation(LocationPoint location) {
        this.location = location;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public List<String> getOutletImage() {
        return outletImage;
    }

    public void setOutletImage(List<String> outletImage) {
        this.outletImage = outletImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getIsPureVeg() {
        return isPureVeg;
    }

    public void setIsPureVeg(Boolean isPureVeg) {
        this.isPureVeg = isPureVeg;
    }

    public Boolean getIsDiscounted() {
        return isDiscounted;
    }

    public void setIsDiscounted(Boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    public List<String> getDiscountArray() {
        return discountArray;
    }

    public void setDiscountArray(List<String> discountArray) {
        this.discountArray = discountArray;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Boolean getIsFood() {
        return isFood;
    }

    public void setIsFood(Boolean isFood) {
        this.isFood = isFood;
    }

    public List<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<String> cuisine) {
        this.cuisine = cuisine;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<Object> getSubSellerId() {
        return subSellerId;
    }

    public void setSubSellerId(List<Object> subSellerId) {
        this.subSellerId = subSellerId;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

}