package com.myfablo.seller.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.text.DecimalFormat;

public class ExtraUtils {

    private Context context;
    DecimalFormat df = new DecimalFormat();

    public ExtraUtils(Context context) {
        this.context = context;
    }

    public void getHapticFeedback() {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(50);
        }
    }

    public void getLongHapticFeedback() {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(5000);
        }
    }


    public void getHapticFeedbackImpulse() {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.EFFECT_TICK));
            } else {
                v.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        } else {
            v.vibrate(50);
        }
    }

    public String getTimeWithMins(String time) {
        return time+" mins";
    }

    public String getCustomName(String variantName, String addonName) {
        return variantName+", "+addonName;
    }

    public String noDiscountMessage(String outletName) {
        return  "Oh Snap! No offers are live on "+outletName+".";
    }

    public String distanceFeeFormatter(String distance) {
        return "Delivery Fee | "+distance;
    }

    public String getCartValue(Integer qty, Float amount) {
        return qty+" item(s) @ ₹"+df.format(amount);
    }

    public String getPriceWithSymbol(Float price) {
        df.setMaximumFractionDigits(2);
        return "₹"+df.format(price);
    }
    public String getPriceWithInrSymbolAndMinus(Float price) {
        df.setMaximumFractionDigits(2);
        return "-₹"+df.format(price);
    }

    public String getDistanceWithUnit(Float distance) {
        return distance+" kms";
    }


}
