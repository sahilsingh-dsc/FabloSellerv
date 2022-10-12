package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.home.outlets.models.OutletItem;
import com.myfablo.seller.utils.Constant;

public class InitPref {

    private Context context;

    public InitPref(Context context) {
        this.context = context;
    }

    public void setDefaultOutlet(OutletItem outlet) {
        if (outlet != null) {
            SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_INIT, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("outletId", outlet.getOutletId());
            editor.putString("outletName", outlet.getOutletName());
            editor.putString("outletImage", outlet.getOutletImage());
            editor.putString("outletArea", outlet.getArea());
            editor.apply();
        }
    }

    public OutletItem getDefaultOutlet() {
        OutletItem outletItem = new OutletItem();
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_INIT, Context.MODE_PRIVATE);
        outletItem.setOutletId(preferences.getString("outletId", "none"));
        outletItem.setOutletName(preferences.getString("outletName", "none"));
        outletItem.setOutletImage(preferences.getString("outletImage", "none"));
        outletItem.setArea(preferences.getString("outletArea", "none"));
        return outletItem;
    }

}
