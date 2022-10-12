package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.myfablo.seller.utils.PrefConst;

public class SellerPref {

    private Context context;

    public SellerPref(Context context) {
        this.context = context;
    }

    public void setSellerData() {
//        if (userLoginResponseData != null) {
//            SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
//            Editor editor = preferences.edit();
//            editor.putInt("user_id", userLoginResponseData.getUserId());
//            editor.putString("first_name", userLoginResponseData.getFirstName());
//            editor.putString("last_name", userLoginResponseData.getLastName());
//            editor.putString("username", userLoginResponseData.getUsername());
//            editor.putString("email", userLoginResponseData.getEmail());
//            editor.putString("company_name", userLoginResponseData.getCompanyName());
//            editor.putString("access_token", userLoginResponseData.getAccessToken());
//            editor.putString("company_address", userLoginResponseData.getCompanyAddress());
//            editor.putString("company_image", userLoginResponseData.getCompanyImage());
//            editor.putString("company_latitude", userLoginResponseData.getCompanyLatitude());
//            editor.putString("company_longitude", userLoginResponseData.getCompanyLongitude());
//            editor.putString("phone", userLoginResponseData.getPhone());
//            editor.putInt("is_active", userLoginResponseData.getIsActive());
//            editor.putInt("is_blocked", userLoginResponseData.getIsBlocked());
//            editor.putString("tookan_shared_secret", userLoginResponseData.getTookanSharedSecret());
//            editor.putInt("registration_type", userLoginResponseData.getRegistrationType());
//            editor.putInt("business_type", userLoginResponseData.getBusinessType());
//            editor.putInt("is_merchant", userLoginResponseData.getIsMerchant());
//            editor.putInt("business_type_selected", userLoginResponseData.getBusinessTypeSelected());
//            editor.putInt("user_type", userLoginResponseData.getUserType());
//            editor.putInt("marketplace_user_id", userLoginResponseData.getMarketplaceUserId());
//            editor.putString("name", userLoginResponseData.getName());
//            editor.apply();
//        }
    }

    public int getUserId() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("user_id", 0);
    }

    public String getFirstName() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("first_name", "none");
    }

    public String getLastName() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("last_name", "none");
    }

    public String getEmail() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("email", "none");
    }

    public String getCompanyName() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("company_name", "none");
    }

    public String getAccessToken() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("access_token", "none");
    }

    public String getCompanyAddress() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("company_address", "none");
    }

    public String getCompanyImage() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("company_image", "none");
    }

    public String getCompanyLatitude() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("company_latitude", "none");
    }

    public String getCompanyLongitude() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("company_longitude", "none");
    }

    public String getPhone() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("phone", "none");
    }

    public int getIsActive() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("is_active", 0);
    }

    public int getIsBlocked() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("is_blocked", 0);
    }

    public String getTookanSharedSecret() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("tookan_shared_secret", "none");
    }

    public int getRegistrationType() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("registration_type", 0);
    }

    public int getBusinessType() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("business_type", 0);
    }

    public int getIsMerchant() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("is_merchant", 0);
    }

    public int getBusinessTypeSelected() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("business_type_selected", 0);
    }

    public int getUserType() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("user_type", 0);
    }

    public int getMarketplaceUserId() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getInt("marketplace_user_id", 0);
    }

    public String getName() {
        SharedPreferences preferences = context.getSharedPreferences(PrefConst.PREF_SELLER, Context.MODE_PRIVATE);
        return preferences.getString("name", "none");
    }

}
