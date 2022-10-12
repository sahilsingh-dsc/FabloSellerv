package com.myfablo.seller.home.outlets.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutletItem {

    @SerializedName("outletId")
    @Expose
    private String outletId;
    @SerializedName("outletName")
    @Expose
    private String outletName;
    @SerializedName("outletImage")
    @Expose
    private String outletImage;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("shopAddress")
    @Expose
    private String shopAddress;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("isClosed")
    @Expose
    private Boolean isClosed;
    @SerializedName("initCount")
    @Expose
    private Integer initCount;
    @SerializedName("pendingCount")
    @Expose
    private Integer pendingCount;
    @SerializedName("preperingCount")
    @Expose
    private Integer preperingCount;
    @SerializedName("readyCount")
    @Expose
    private Integer readyCount;
    @SerializedName("dispatchedCount")
    @Expose
    private Integer dispatchedCount;
    @SerializedName("deliveredCount")
    @Expose
    private Integer deliveredCount;
    @SerializedName("cancelledCount")
    @Expose
    private Integer cancelledCount;


    /**
     * No args constructor for use in serialization
     */
    public OutletItem() {
    }

    /**
     *
     * @param area
     * @param outletId
     * @param latitude
     * @param initCount
     * @param dispatchedCount
     * @param shopAddress
     * @param preperingCount
     * @param outletName
     * @param outletImage
     * @param isClosed
     * @param pendingCount
     * @param readyCount
     * @param cancelledCount
     * @param deliveredCount
     * @param longitude
     */

    public OutletItem(String outletId, String outletName, String outletImage, String area, String shopAddress, Float longitude, Float latitude, Boolean isClosed, Integer initCount, Integer pendingCount, Integer preperingCount, Integer readyCount, Integer dispatchedCount, Integer deliveredCount, Integer cancelledCount) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.outletImage = outletImage;
        this.area = area;
        this.shopAddress = shopAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isClosed = isClosed;
        this.initCount = initCount;
        this.pendingCount = pendingCount;
        this.preperingCount = preperingCount;
        this.readyCount = readyCount;
        this.dispatchedCount = dispatchedCount;
        this.deliveredCount = deliveredCount;
        this.cancelledCount = cancelledCount;
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

    public String getOutletImage() {
        return outletImage;
    }

    public void setOutletImage(String outletImage) {
        this.outletImage = outletImage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public Integer getInitCount() {
        return initCount;
    }

    public void setInitCount(Integer initCount) {
        this.initCount = initCount;
    }

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

    public Integer getPreperingCount() {
        return preperingCount;
    }

    public void setPreperingCount(Integer preperingCount) {
        this.preperingCount = preperingCount;
    }

    public Integer getReadyCount() {
        return readyCount;
    }

    public void setReadyCount(Integer readyCount) {
        this.readyCount = readyCount;
    }

    public Integer getDispatchedCount() {
        return dispatchedCount;
    }

    public void setDispatchedCount(Integer dispatchedCount) {
        this.dispatchedCount = dispatchedCount;
    }

    public Integer getDeliveredCount() {
        return deliveredCount;
    }

    public void setDeliveredCount(Integer deliveredCount) {
        this.deliveredCount = deliveredCount;
    }

    public Integer getCancelledCount() {
        return cancelledCount;
    }

    public void setCancelledCount(Integer cancelledCount) {
        this.cancelledCount = cancelledCount;
    }
}