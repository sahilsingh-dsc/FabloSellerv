package com.myfablo.seller.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.myfablo.seller.home.outlets.models.OutletItem;
import com.myfablo.seller.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class InitPref {

    private Context context;

    public InitPref(Context context) {
        this.context = context;
    }

    public OutletItem getDefaultOutlet() {
        OutletItem outletItem = new OutletItem();
        SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_INIT, Context.MODE_PRIVATE);
        outletItem.setOutletId(preferences.getString("outletId", "none"));
        outletItem.setOutletName(preferences.getString("outletName", "none"));
        List<String> imageList = new ArrayList<>();
        imageList.add(preferences.getString("outletImage", "none"));
        outletItem.setOutletImage(imageList);
        outletItem.setArea(preferences.getString("outletArea", "none"));
        return outletItem;
    }

    public void setDefaultOutlet(OutletItem outlet) {
        if (outlet != null) {
            SharedPreferences preferences = context.getSharedPreferences(Constant.PREF_INIT, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("outletId", outlet.getOutletId());
            editor.putString("outletName", outlet.getOutletName());
            editor.putString("outletImage", outlet.getOutletImage().get(0));
            editor.putString("outletArea", outlet.getArea());
            editor.apply();
        }
    }

}
