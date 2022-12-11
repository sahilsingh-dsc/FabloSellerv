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
import com.myfablo.seller.manage.menu.models.StockUpdate;

import org.greenrobot.eventbus.EventBus;

public class ProductStockAlert {

    public static ProductStockAlert productStockAlert = null;
    private Dialog mDialog;

    public static ProductStockAlert getInstance() {
        if (productStockAlert == null) {
            productStockAlert = new ProductStockAlert();
        }
        return productStockAlert;
    }

    public void showAlert(Context context, boolean status, String productId, String productName) {
        mDialog = new Dialog(context);
        context.setTheme(R.style.Theme_FabloSeller);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_alert_product_stock);
        MaterialButton btnYes = mDialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = mDialog.findViewById(R.id.btnNo);
        TextView tvNotice = mDialog.findViewById(R.id.tvNotice);
        TextView tvProductName = mDialog.findViewById(R.id.tvProductName);
        tvProductName.setText(productName);

        if (status) {
            tvNotice.setText("Once marked out stock this product will be invisible to customers.");
            btnYes.setText("Out Stock");
        } else {
            tvNotice.setText("Once marked in stock this product will be visible to customers.");
            btnYes.setText("In Stock");
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
                    EventBus.getDefault().post(new StockUpdate(productId));
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
