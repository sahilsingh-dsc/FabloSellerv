package com.myfablo.seller.auth.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizedPersonDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     */
    public AuthorizedPersonDetails() {
    }

    /**
     * @param phone
     * @param name
     * @param pan
     * @param email
     */
    public AuthorizedPersonDetails(String name, String pan, String phone, String email) {
        super();
        this.name = name;
        this.pan = pan;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}