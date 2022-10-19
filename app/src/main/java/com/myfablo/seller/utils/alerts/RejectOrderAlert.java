package com.myfablo.seller.utils.alerts;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.OrderStatusChangeUtil;

import org.greenrobot.eventbus.EventBus;

public class RejectOrderAlert {

    public static RejectOrderAlert rejectOrderAlert = null;
    private Dialog mDialog;

    public static RejectOrderAlert getInstance() {
        if (rejectOrderAlert == null) {
            rejectOrderAlert = new RejectOrderAlert();
        }
        return rejectOrderAlert;
    }

    public void showAlert(Context context, String orderId) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_reject_order);
        MaterialButton btnYes = mDialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = mDialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderStatusChangeRequest orderStatusChangeRequest = new OrderStatusChangeRequest(orderId, Constant.ORDER_STATUS_CANCELLED);
                OrderStatusChangeUtil orderStatusChangeUtil = new OrderStatusChangeUtil(context);
                orderStatusChangeUtil.changeOrderStatus(orderStatusChangeRequest);
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
