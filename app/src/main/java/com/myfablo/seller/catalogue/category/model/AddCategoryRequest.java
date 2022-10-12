package com.myfablo.seller.catalogue.category.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCategoryRequest {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     */
    public AddCategoryRequest() {
    }

    /**
     * @param marketplaceUserId
     * @param apiKey
     * @param imageUrl
     * @param name
     * @param description
     * @param userId
     */
    public AddCategoryRequest(String apiKey, Integer marketplaceUserId, Integer userId, String name, String description, String imageUrl) {
        super();
        this.apiKey = apiKey;
        this.marketplaceUserId = marketplaceUserId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Integer marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

}