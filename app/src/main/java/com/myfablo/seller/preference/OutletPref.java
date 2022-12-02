package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.home.outlets.models.single.OutletDetailsItem;
import com.myfablo.seller.utils.Constant;

public class OutletPref {

    private Context context;

    public OutletPref(Context context) {
        this.context = context;
    }

    public void setDefaultOutletDetails(OutletDetailsItem outletDetails) {
        if (outletDetails != null) {
            SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("outletId", outletDetails.getOutletId());
            editor.putString("outletName", outletDetails.getOutletName());
            editor.putString("outletImage", outletDetails.getOutletImage().get(0));
            editor.putString("type", outletDetails.getType());
            editor.putString("preparationTime", outletDetails.getPreparationTime());
            editor.putString("area", outletDetails.getArea());
            editor.putBoolean("isPureVeg", outletDetails.getIsPureVeg());
            editor.putBoolean("isDiscounted", outletDetails.getIsDiscounted());
            editor.putBoolean("isFood", outletDetails.getIsFood());
            editor.putString("shopAddress", outletDetails.getShopAddress());
            editor.putString("sellerId", outletDetails.getSellerId());
            editor.putBoolean("isClosed", outletDetails.getIsClosed());
            editor.putBoolean("isVerified", outletDetails.getIsVerified());
            editor.putBoolean("isVisible", outletDetails.getIsVisible());
            editor.putBoolean("isFeatured", outletDetails.getIsFeatured());
            editor.putString("outletTiming0", outletDetails.getOpeningHours().get0().get(0));
            editor.putString("outletTiming1", outletDetails.getOpeningHours().get1().get(0));
            editor.putString("outletTiming2", outletDetails.getOpeningHours().get2().get(0));
            editor.putString("outletTiming3", outletDetails.getOpeningHours().get3().get(0));
            editor.putString("outletTiming4", outletDetails.getOpeningHours().get4().get(0));
            editor.putString("outletTiming5", outletDetails.getOpeningHours().get5().get(0));
            editor.putString("outletTiming6", outletDetails.getOpeningHours().get6().get(0));
            editor.putFloat("outletLat", outletDetails.getLocation().getCoordinates().get(1));
            editor.putFloat("outletLng", outletDetails.getLocation().getCoordinates().get(0));
            editor.apply();
        }
    }

    public String getOutletId() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletId", "none");
    }

    public String getOutletName() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletName", "none");
    }

    public String getOutletImage() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletImage", "none");
    }

    public String getType() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("type", "none");
    }

    public String getPreparationTime() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("preparationTime", "none");
    }

    public String getOutletArea() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("area", "none");
    }

    public boolean getIsPureVeg() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isPureVeg", false);
    }

    public boolean getIsDiscounted() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isDiscounted", false);
    }

    public boolean getIsFood() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isFood", false);
    }

    public String getOutletAddress() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("shopAddress", "none");
    }

    public String getSellerId() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("sellerId", "none");
    }

    public boolean getIsClosed() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isClosed", false);
    }

    public boolean getIsVerified() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isVerified", false);
    }

    public boolean getIsVisible() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isVisible", false);
    }

    public boolean getIsFeatured() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getBoolean("isFeatured", false);
    }

    public String getOpeningHours0() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming0", "none");
    }

    public String getOpeningHours1() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming1", "none");
    }

    public String getOpeningHours2() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming2", "none");
    }

    public String getOpeningHours3() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming3", "none");
    }

    public String getOpeningHours4() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming4", "none");
    }

    public String getOpeningHours5() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming5", "none");
    }

    public String getOpeningHours6() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getString("outletTiming6", "none");
    }

    public Float getOutletLat() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getFloat("outletLat", 0);
    }

    public Float getOutletLng() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        return preferences.getFloat("outletLng", 0);
    }

    public void clearData() {
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_OUTLET, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


}
