package com.myfablo.seller.manage.menu.addons;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;

public class AddonsCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvCategoryName;
    TextView tvSelection;
    ImageView ivShowHideProducts;
    RecyclerView recyclerProduct;
    private Context context;
    private Item addonsCategory;

    public AddonsCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        tvSelection = itemView.findViewById(R.id.tvSelection);
        ivShowHideProducts = itemView.findViewById(R.id.ivShowHideProducts);
        recyclerProduct = itemView.findViewById(R.id.recyclerProduct);
    }

    public void bindData(Context mContext, Item addonsCategoryData) {
        context = mContext;
        addonsCategory = addonsCategoryData;

        initView();
    }

    private void initView() {
        initClick();
        initRecycler();
        showViewData();
    }

    private void initClick() {
        ivShowHideProducts.setOnClickListener(this);
    }

    private void initRecycler() {
        recyclerProduct.setLayoutManager(new LinearLayoutManager(context));
    }

    private void showViewData() {
        tvCategoryName.setText(addonsCategory.getCategoryName() + " (" + addonsCategory.getProductList().size() + ")");
        tvSelection.setText("Minimum selection " + addonsCategory.getMinSelection() + " / Maximum selection " + addonsCategory.getMaxSelection());
        AddonsProductRecyclerAdapter addonsProductRecyclerAdapter = new AddonsProductRecyclerAdapter(context, addonsCategory.getProductList());
        recyclerProduct.setAdapter(addonsProductRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == ivShowHideProducts) {
            if (recyclerProduct.getVisibility() == View.VISIBLE) {
                recyclerProduct.setVisibility(View.GONE);
                ivShowHideProducts.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_arrow_down));
            } else {
                recyclerProduct.setVisibility(View.VISIBLE);
                ivShowHideProducts.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_arrow_up));
            }
        }
    }
}
