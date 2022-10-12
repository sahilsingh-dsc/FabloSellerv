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

public class OrderNotificationAlert {

    public static OrderNotificationAlert orderNotificationAlert = null;
    private Dialog mDialog;

    public static OrderNotificationAlert getInstance() {
        if (orderNotificationAlert == null) {
            orderNotificationAlert = new OrderNotificationAlert();
        }
        return orderNotificationAlert;
    }

    public void showAlert(Context context, boolean status) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_order_notification);
        MaterialButton btnYes = mDialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = mDialog.findViewById(R.id.btnNo);
        TextView tvNotice = mDialog.findViewById(R.id.tvNotice);

        if (status) {
            tvNotice.setText("Once turned off you will not receive any order notifications until you turn it on.");
            btnYes.setText("Turn Off");
        } else {
            tvNotice.setText("Once turned on you will receive all the order notifications until you turn it off.");
            btnYes.setText("Turn On");
        }

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post("orderNotification");
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
