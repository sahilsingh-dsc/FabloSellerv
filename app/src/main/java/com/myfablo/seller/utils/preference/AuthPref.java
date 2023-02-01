package com.myfablo.seller.utils.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.auth.models.UserLoginResponse;
import com.myfablo.seller.utils.Constant;

public class AuthPref {

    private Context context;

    public AuthPref(Context context) {
        this.context = context;
    }

    public void setUser(String token, String sellerId) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.putString("sellerId", sellerId);
        editor.apply();
    }

    public String getAuthToken() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        return preferences.getString("token", "none");
    }

    public String getSellerId() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        return preferences.getString("sellerId", "none");
    }

    public String getBearerToken() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        return "Bearer "+preferences.getString("token", "none");
    }

    public void setOnboardStatus(boolean status) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(UserLoginResponse.RES_AUTH_ONBOARD, status);
        editor.apply();
    }

    public boolean getOnboardStatus() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        return preferences.getBoolean(UserLoginResponse.RES_AUTH_ONBOARD, false);
    }

    public void clearData() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
