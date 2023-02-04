package com.myfablo.seller.utils;

import android.content.Context;

public class ResponseFormatter {

    private Context context;

    public ResponseFormatter(Context context) {
        this.context = context;
    }

    public String getPriceWithSymbol(Float price) {
        return "₹" + price;
    }

    public String getMinMaxSelection(Integer min, Integer max) {
        return "Minimum selection "+min+" / Maximum Selection "+max;
    }
}
