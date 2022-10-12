package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

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

}
