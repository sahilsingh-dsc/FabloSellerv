package com.myfablo.seller.auth.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicDetails {

    @SerializedName("sellerName")
    @Expose
    private String sellerName;
    @SerializedName("tradeName")
    @Expose
    private String tradeName;
    @SerializedName("sellerType")
    @Expose
    private String sellerType;
    @SerializedName("isGst")
    @Expose
    private Boolean isGst;
    @SerializedName("gstNo")
    @Expose
    private String gstNo;
    @SerializedName("panNumber")
    @Expose
    private String panNumber;
    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * No args constructor for use in serialization
     */
    public BasicDetails() {
    }

    /**
     * @param tradeName
     * @param isGst
     * @param phone
     * @param sellerName
     * @param gstNo
     * @param panNumber
     * @param sellerType
     */
    public BasicDetails(String sellerName, String tradeName, String sellerType, Boolean isGst, String gstNo, String panNumber, String phone) {
        super();
        this.sellerName = sellerName;
        this.tradeName = tradeName;
        this.sellerType = sellerType;
        this.isGst = isGst;
        this.gstNo = gstNo;
        this.panNumber = panNumber;
        this.phone = phone;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public Boolean getIsGst() {
        return isGst;
    }

    public void setIsGst(Boolean isGst) {
        this.isGst = isGst;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}