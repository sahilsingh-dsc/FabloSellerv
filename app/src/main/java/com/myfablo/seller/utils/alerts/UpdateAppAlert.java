package com.myfablo.seller.utils.alerts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;

import org.greenrobot.eventbus.EventBus;

public class UpdateAppAlert {

    public static UpdateAppAlert updateAppAlert = null;
    private Dialog mDialog;

    public static UpdateAppAlert getInstance() {
        if (updateAppAlert == null) {
            updateAppAlert = new UpdateAppAlert();
        }
        return updateAppAlert;
    }

    public void showAlert(Context context) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_update_app);
        MaterialButton btnYes = mDialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = mDialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                hideAlert();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideAlert();
            }
        });

        mDialog.show();
    }

    public void hideAlert() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
