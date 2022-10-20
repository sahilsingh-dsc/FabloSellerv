package com.myfablo.seller.utils.alerts;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;

import org.greenrobot.eventbus.EventBus;

public class OhSnapErrorAlert {

    public static OhSnapErrorAlert ohSnapErrorAlert = null;
    private Dialog mDialog;

    public static OhSnapErrorAlert getInstance() {
        if (ohSnapErrorAlert == null) {
            ohSnapErrorAlert = new OhSnapErrorAlert();
        }
        return ohSnapErrorAlert;
    }

    public void showAlert(Context context, String notice) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_oh_snap);
        MaterialButton btnDismiss = mDialog.findViewById(R.id.btnDismiss);
        TextView tvNotice = mDialog.findViewById(R.id.tvNotice);

        tvNotice.setText(notice);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
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
