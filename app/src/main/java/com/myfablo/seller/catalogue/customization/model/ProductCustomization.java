package com.myfablo.seller.catalogue.customization.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCustomization {

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
    private List<ProductCustomizationOption> customizeOptions = null;
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

    public ProductCustomization() {
    }

    public ProductCustomization(Integer customizeId, String name, Integer isCheckBox, String type, Integer minimumSelection, Integer minimumSelectionRequired, List<ProductCustomizationOption> customizeOptions, Integer isVariant, Object parentCustomizeId, Object parentCustId, Integer mappingEnabled) {
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

    public List<ProductCustomizationOption> getCustomizeOptions() {
        return customizeOptions;
    }

    public void setCustomizeOptions(List<ProductCustomizationOption> customizeOptions) {
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
}
