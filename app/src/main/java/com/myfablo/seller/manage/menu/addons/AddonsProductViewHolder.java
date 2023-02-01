package com.myfablo.seller.manage.menu.addons;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Product;

public class AddonsProductViewHolder extends RecyclerView.ViewHolder {

    ImageView ivServingType;
    TextView tvItemName;
    TextView tvItemPrice;

    private Context context;
    private Product product;

    public AddonsProductViewHolder(@NonNull View itemView) {
        super(itemView);
        ivServingType = itemView.findViewById(R.id.ivServingType);
        tvItemName = itemView.findViewById(R.id.tvItemName);
        tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
    }

    public void bindData(Context mContext, Product productData) {
        context = mContext;
        product = productData;
        initView();
    }

    private void initView() {
        showViewData();
    }

    private void showViewData() {
        tvItemName.setText(product.getProductName());
        tvItemPrice.setText("₹" + product.getProductPrice());
    }
}
