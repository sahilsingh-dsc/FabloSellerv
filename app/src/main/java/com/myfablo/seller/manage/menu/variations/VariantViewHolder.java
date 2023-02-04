package com.myfablo.seller.manage.menu.variations;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.products.ProductDetailsActivity;
import com.myfablo.seller.utils.ResponseFormatter;

public class VariantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView ivServingType;
    TextView tvItemName;
    TextView tvItemPrice;
    LinearLayout lhHasSubVariation;

    private Context context;
    private Variant variant;

    public VariantViewHolder(@NonNull View itemView) {
        super(itemView);
        ivServingType = itemView.findViewById(R.id.ivServingType);
        tvItemName = itemView.findViewById(R.id.tvItemName);
        tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
        lhHasSubVariation = itemView.findViewById(R.id.lhHasSubVariation);
    }

    public void bindData(Context mContext, Variant variantData) {
        context = mContext;
        variant = variantData;

        initView();
    }

    private void initView() {
        initClick();
        showViewData();
    }

    private void initClick() {
        lhHasSubVariation.setOnClickListener(this);
    }

    private void showViewData() {
        showVariantDetails();
        checkSubVariant();
    }

    private void showVariantDetails() {
        tvItemName.setText(variant.getVariantName());
        tvItemPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(variant.getDisplayPrice()));
    }

    private void checkSubVariant() {
        if (variant.getHasCustomization()) {
            lhHasSubVariation.setVisibility(View.VISIBLE);
        } else {
            lhHasSubVariation.setVisibility(View.GONE);
        }
    }

    private void showSubVariantDetails() {
        SubVariantBottomSheet bottomSheet = new SubVariantBottomSheet();
        Gson gson = new Gson();
        String variantJson = gson.toJson(variant);
        Bundle bundle = new Bundle();
        bundle.putString("variant", variantJson);
        bottomSheet.setArguments(bundle);
        bottomSheet.show(((ProductDetailsActivity)context).getSupportFragmentManager(), "subVariant");
    }

    @Override
    public void onClick(View view) {
        if (view == lhHasSubVariation) {
            showSubVariantDetails();
        }
    }
}
