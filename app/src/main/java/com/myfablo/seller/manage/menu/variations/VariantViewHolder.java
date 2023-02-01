package com.myfablo.seller.manage.menu.variations;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.utils.ResponseFormatter;

public class VariantViewHolder extends RecyclerView.ViewHolder {

    ImageView ivServingType;
    TextView tvItemName;
    TextView tvItemPrice;
    TextView tvSubVariantName;
    RecyclerView recyclerSubVariant;

    private Context context;
    private Variant variant;

    public VariantViewHolder(@NonNull View itemView) {
        super(itemView);
        ivServingType = itemView.findViewById(R.id.ivServingType);
        tvItemName = itemView.findViewById(R.id.tvItemName);
        tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
        tvSubVariantName = itemView.findViewById(R.id.tvSubVariantName);
        recyclerSubVariant = itemView.findViewById(R.id.recyclerSubVariant);
    }

    public void bindData(Context mContext, Variant variantData) {
        context = mContext;
        variant = variantData;

        initView();
    }

    private void initView() {
        initRecycler();
        showViewData();
    }

    private void showViewData() {
        showVariantDetails();
        checkSubVariant();
    }

    private void initRecycler() {
        recyclerSubVariant.setLayoutManager(new LinearLayoutManager(context));
    }

    private void showVariantDetails() {
        tvItemName.setText(variant.getVariantName());
        tvItemPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(variant.getDisplayPrice()));
    }

    private void checkSubVariant() {
        if (variant.getHasCustomization()) {
            recyclerSubVariant.setVisibility(View.VISIBLE);
            tvSubVariantName.setVisibility(View.VISIBLE);
            tvSubVariantName.setText(variant.getVariantDetail().getVariationName());
            showSubVariantDetails();
        } else {
            recyclerSubVariant.setVisibility(View.GONE);
            tvSubVariantName.setVisibility(View.GONE);
        }
    }

    private void showSubVariantDetails() {
        VariantRecyclerAdapter variantRecyclerAdapter = new VariantRecyclerAdapter(context, variant.getVariantDetail().getVariantList());
        recyclerSubVariant.setAdapter(variantRecyclerAdapter);
    }

}
