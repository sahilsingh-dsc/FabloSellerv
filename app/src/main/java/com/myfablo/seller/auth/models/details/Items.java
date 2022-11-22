package com.myfablo.seller.auth.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("basicDetails")
    @Expose
    private BasicDetails basicDetails;
    @SerializedName("licenseDetails")
    @Expose
    private LicenseDetails licenseDetails;
    @SerializedName("authorizedPersonDetails")
    @Expose
    private AuthorizedPersonDetails authorizedPersonDetails;
    @SerializedName("sellerId")
    @Expose
    private String sellerId;
    @SerializedName("verificationStatus")
    @Expose
    private String verificationStatus;
    @SerializedName("yeloId")
    @Expose
    private List<Object> yeloId = null;
    @SerializedName("agentId")
    @Expose
    private String agentId;
    @SerializedName("numberOfShop")
    @Expose
    private Integer numberOfShop;
    @SerializedName("outletList")
    @Expose
    private List<Object> outletList = null;

    /**
     * No args constructor for use in serialization
     */
    public Items() {
    }

    /**
     * @param licenseDetails
     * @param agentId
     * @param sellerId
     * @param outletList
     * @param verificationStatus
     * @param numberOfShop
     * @param yeloId
     * @param basicDetails
     * @param authorizedPersonDetails
     */
    public Items(BasicDetails basicDetails, LicenseDetails licenseDetails, AuthorizedPersonDetails authorizedPersonDetails, String sellerId, String verificationStatus, List<Object> yeloId, String agentId, Integer numberOfShop, List<Object> outletList) {
        super();
        this.basicDetails = basicDetails;
        this.licenseDetails = licenseDetails;
        this.authorizedPersonDetails = authorizedPersonDetails;
        this.sellerId = sellerId;
        this.verificationStatus = verificationStatus;
        this.yeloId = yeloId;
        this.agentId = agentId;
        this.numberOfShop = numberOfShop;
        this.outletList = outletList;
    }

    public BasicDetails getBasicDetails() {
        return basicDetails;
    }

    public void setBasicDetails(BasicDetails basicDetails) {
        this.basicDetails = basicDetails;
    }

    public LicenseDetails getLicenseDetails() {
        return licenseDetails;
    }

    public void setLicenseDetails(LicenseDetails licenseDetails) {
        this.licenseDetails = licenseDetails;
    }

    public AuthorizedPersonDetails getAuthorizedPersonDetails() {
        return authorizedPersonDetails;
    }

    public void setAuthorizedPersonDetails(AuthorizedPersonDetails authorizedPersonDetails) {
        this.authorizedPersonDetails = authorizedPersonDetails;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public List<Object> getYeloId() {
        return yeloId;
    }

    public void setYeloId(List<Object> yeloId) {
        this.yeloId = yeloId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getNumberOfShop() {
        return numberOfShop;
    }

    public void setNumberOfShop(Integer numberOfShop) {
        this.numberOfShop = numberOfShop;
    }

    public List<Object> getOutletList() {
        return outletList;
    }

    public void setOutletList(List<Object> outletList) {
        this.outletList = outletList;
    }

}