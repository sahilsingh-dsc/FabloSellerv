package com.myfablo.seller.utils.alerts;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;
import com.myfablo.seller.home.outlets.models.OutletItem;

import org.greenrobot.eventbus.EventBus;

public class OutletStatusAlert {

    public static OutletStatusAlert outletStatusAlert = null;
    private Dialog mDialog;

    public static OutletStatusAlert getInstance() {
        if (outletStatusAlert == null) {
            outletStatusAlert = new OutletStatusAlert();
        }
        return outletStatusAlert;
    }

    public void showAlert(Context context, boolean status, OutletItem outletItem) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_outlet_status);
        MaterialButton btnYes = mDialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = mDialog.findViewById(R.id.btnNo);
        TextView tvNotice = mDialog.findViewById(R.id.tvNotice);
        TextView tvOutletName = mDialog.findViewById(R.id.tvOutletName);
        tvOutletName.setText(outletItem.getOutletName());

        if (!status) {
            tvNotice.setText("Once turned off this outlet will not accept orders from customers.");
            btnYes.setText("Turn Off");
        } else {
            tvNotice.setText("Once turned on this outlet will accept orders from customers.");
            btnYes.setText("Turn On");
        }

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideAlert();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (outletItem != null) {
                    EventBus.getDefault().post(outletItem);
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
