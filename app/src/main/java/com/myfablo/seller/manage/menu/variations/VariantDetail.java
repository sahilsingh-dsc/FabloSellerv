package com.myfablo.seller.manage.menu.variations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VariantDetail {

    @SerializedName("variationId")
    @Expose
    private String variationId;
    @SerializedName("variationName")
    @Expose
    private String variationName;
    @SerializedName("minSelection")
    @Expose
    private Integer minSelection;
    @SerializedName("maxSelection")
    @Expose
    private Integer maxSelection;
    @SerializedName("variantList")
    @Expose
    private List<Variant> variantList;

    /**
     * No args constructor for use in serialization
     */
    public VariantDetail() {
    }

    /**
     * @param variationId
     * @param variationName
     * @param minSelection
     * @param variantList
     * @param maxSelection
     */
    public VariantDetail(String variationId, String variationName, Integer minSelection, Integer maxSelection, List<Variant> variantList) {
        super();
        this.variationId = variationId;
        this.variationName = variationName;
        this.minSelection = minSelection;
        this.maxSelection = maxSelection;
        this.variantList = variantList;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public Integer getMinSelection() {
        return minSelection;
    }

    public void setMinSelection(Integer minSelection) {
        this.minSelection = minSelection;
    }

    public Integer getMaxSelection() {
        return maxSelection;
    }

    public void setMaxSelection(Integer maxSelection) {
        this.maxSelection = maxSelection;
    }

    public List<Variant> getVariantList() {
        return variantList;
    }

    public void setVariantList(List<Variant> variantList) {
        this.variantList = variantList;
    }

}