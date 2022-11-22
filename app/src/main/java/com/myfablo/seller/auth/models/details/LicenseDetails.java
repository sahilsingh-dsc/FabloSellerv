package com.myfablo.seller.auth.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LicenseDetails {

    @SerializedName("nameOnlicence")
    @Expose
    private String nameOnlicence;
    @SerializedName("licenceNumber")
    @Expose
    private String licenceNumber;
    @SerializedName("licenceType")
    @Expose
    private String licenceType;
    @SerializedName("licenceImage")
    @Expose
    private String licenceImage;
    @SerializedName("issuedOn")
    @Expose
    private String issuedOn;
    @SerializedName("tenure")
    @Expose
    private String tenure;

    /**
     * No args constructor for use in serialization
     */
    public LicenseDetails() {
    }

    /**
     * @param nameOnlicence
     * @param licenceNumber
     * @param licenceType
     * @param licenceImage
     * @param tenure
     * @param issuedOn
     */
    public LicenseDetails(String nameOnlicence, String licenceNumber, String licenceType, String licenceImage, String issuedOn, String tenure) {
        super();
        this.nameOnlicence = nameOnlicence;
        this.licenceNumber = licenceNumber;
        this.licenceType = licenceType;
        this.licenceImage = licenceImage;
        this.issuedOn = issuedOn;
        this.tenure = tenure;
    }

    public String getNameOnlicence() {
        return nameOnlicence;
    }

    public void setNameOnlicence(String nameOnlicence) {
        this.nameOnlicence = nameOnlicence;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicenceImage() {
        return licenceImage;
    }

    public void setLicenceImage(String licenceImage) {
        this.licenceImage = licenceImage;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

}