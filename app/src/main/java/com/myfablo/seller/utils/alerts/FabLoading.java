package com.myfablo.seller.utils.alerts;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.myfablo.seller.R;

public class FabLoading {

    public static FabLoading fabLoading = null;
    private Dialog mDialog;

    public static FabLoading getInstance() {
        if (fabLoading == null) {
            fabLoading = new FabLoading();
        }
        return fabLoading;
    }

    public void showProgress(Context context) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
