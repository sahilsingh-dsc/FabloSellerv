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

public class SuccessAlert {

    public static SuccessAlert successAlert = null;
    private Dialog mDialog;

    public static SuccessAlert getInstance() {
        if (successAlert == null) {
            successAlert = new SuccessAlert();
        }
        return successAlert;
    }

    public void showAlert(Context context, String title, String notice, boolean backStack, String tag) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_success);
        MaterialButton btnDismiss = mDialog.findViewById(R.id.btnDismiss);
        TextView tvNotice = mDialog.findViewById(R.id.tvNotice);
        TextView tvTitle = mDialog.findViewById(R.id.tvTitle);

        tvNotice.setText(notice);
        tvTitle.setText(title);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backStack) {
                    EventBus.getDefault().post(tag);
                    hideAlert();
                } else {
                    hideAlert();
                }

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
