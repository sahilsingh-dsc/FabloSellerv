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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.models.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FoodMenuProductRecyclerAdapterBackup extends RecyclerView.Adapter<FoodMenuProductRecyclerAdapterBackup.ViewHolder> {

    private Context context;
    private List<Product> productList;

    private static final String TAG = "FoodMenuProductRecycler";

    public FoodMenuProductRecyclerAdapterBackup(Context context, List<Product> productList) {
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
                holder.tvCustomizable.setVisibility(View.VISIBLE);
            } else {
                holder.tvCustomizable.setVisibility(View.GONE);
            }

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
        private TextView tvCustomizable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivServingType = itemView.findViewById(R.id.ivServingType);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            cardImage = itemView.findViewById(R.id.cardImage);

        }
    }
}
