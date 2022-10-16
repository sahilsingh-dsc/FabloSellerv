package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.auth.model.UserLoginResponse;
import com.myfablo.seller.utils.Constant;

public class AuthPref {

    private Context context;

    public AuthPref(Context context) {
        this.context = context;
    }

    public void setUser(String token) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getAuthToken() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_AUTH, Context.MODE_PRIVATE);
        return preferences.getString("token", "none");
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

}
