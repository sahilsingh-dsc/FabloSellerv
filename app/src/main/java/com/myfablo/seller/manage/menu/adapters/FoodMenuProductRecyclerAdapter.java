package com.myfablo.seller.manage.menu.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.fragments.CustomizationBottomSheet;
import com.myfablo.seller.manage.menu.models.Product;

import java.util.List;

public class FoodMenuProductRecyclerAdapter extends RecyclerView.Adapter<FoodMenuProductRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;

    private static final String TAG = "FoodMenuProductRecycler";

    public FoodMenuProductRecyclerAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        if (product != null) {
            holder.tvProductPrice.setText("₹"+product.getProductPrice());
            holder.tvProductName.setText(product.getProductName());
            if (product.getProductDesc().isEmpty()) {
                holder.tvProductDescription.setVisibility(View.GONE);
            }
            holder.tvProductDescription.setText(product.getProductDesc());
            if (product.getProductImage().isEmpty()) {
                holder.cardImage.setVisibility(View.GONE);
            } else {
                holder.cardImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(product.getProductImage()).into(holder.ivProductImage);
            }

            if (product.getHasCustomization()) {
                holder.tvCustomization.setVisibility(View.VISIBLE);
            } else {
                holder.tvCustomization.setVisibility(View.GONE);
            }

            holder.tvCustomization.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("product_id", product.getProductId());
                    bundle.putString("name", product.getProductId());
                    bundle.putString("price", product.getProductId());
                    CustomizationBottomSheet customizationBottomSheet = new CustomizationBottomSheet();
                    customizationBottomSheet.setArguments(bundle);
                    customizationBottomSheet.show(((AppCompatActivity)context).getSupportFragmentManager(), "custom");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivServingType;
        private ImageView ivProductImage;
        private TextView tvProductName;
        private TextView tvProductPrice;
        private TextView tvProductDescription;
        private MaterialCardView cardImage;
        private TextView tvCustomization;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivServingType = itemView.findViewById(R.id.ivServingType);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvCustomization = itemView.findViewById(R.id.tvCustomization);
            cardImage = itemView.findViewById(R.id.cardImage);

        }
    }
}
