package com.myfablo.seller.manage.menu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customization {

    @SerializedName("customizationId")
    @Expose
    private String customizationId;
    @SerializedName("customizationName")
    @Expose
    private String customizationName;
    @SerializedName("customItem")
    @Expose
    private List<CustomItem> customItem = null;
    @SerializedName("multipleSelection")
    @Expose
    private Boolean multipleSelection;
    @SerializedName("noOfSelection")
    @Expose
    private Integer noOfSelection;
    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;

    /**
     * No args constructor for use in serialization
     */
    public Customization() {
    }

    /**
     * @param customItem
     * @param noOfSelection
     * @param isRequired
     * @param customizationId
     * @param customizationName
     * @param multipleSelection
     */
    public Customization(String customizationId, String customizationName, List<CustomItem> customItem, Boolean multipleSelection, Integer noOfSelection, Boolean isRequired) {
        super();
        this.customizationId = customizationId;
        this.customizationName = customizationName;
        this.customItem = customItem;
        this.multipleSelection = multipleSelection;
        this.noOfSelection = noOfSelection;
        this.isRequired = isRequired;
    }

    public String getCustomizationId() {
        return customizationId;
    }

    public void setCustomizationId(String customizationId) {
        this.customizationId = customizationId;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public List<CustomItem> getCustomItem() {
        return customItem;
    }

    public void setCustomItem(List<CustomItem> customItem) {
        this.customItem = customItem;
    }

    public Boolean getMultipleSelection() {
        return multipleSelection;
    }

    public void setMultipleSelection(Boolean multipleSelection) {
        this.multipleSelection = multipleSelection;
    }

    public Integer getNoOfSelection() {
        return noOfSelection;
    }

    public void setNoOfSelection(Integer noOfSelection) {
        this.noOfSelection = noOfSelection;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

}