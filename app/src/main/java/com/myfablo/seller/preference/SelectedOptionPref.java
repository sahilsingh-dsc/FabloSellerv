package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.utils.Constant;

public class SelectedOptionPref {

    private Context context;

    public SelectedOptionPref(Context context) {
        this.context = context;
    }

    public void setSelectedOutletOption(String outletType) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_SELECTED_OPTION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("outletType", outletType);
        editor.apply();
    }

    public void setSelectedOrderStatusOption(String orderStatus) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_SELECTED_OPTION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("orderStatus", orderStatus);
        editor.apply();
    }

    public String getSelectedOutletOption() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_SELECTED_OPTION, Context.MODE_PRIVATE);
        return preferences.getString("outletType", "online");
    }

    public String getSelectedOrderStatusOption() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_SELECTED_OPTION, Context.MODE_PRIVATE);
        return preferences.getString("orderStatus", "preparing");
    }

    public void clearData() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_SELECTED_OPTION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
