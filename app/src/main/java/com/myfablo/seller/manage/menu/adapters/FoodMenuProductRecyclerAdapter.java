package com.myfablo.seller.manage.menu.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.myfablo.seller.manage.menu.products.ProductDetailsActivity;
import com.myfablo.seller.utils.alerts.ProductStockAlert;
import com.suke.widget.SwitchButton;

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
            if (product.getProductDesc() == null || product.getProductDesc().isEmpty()) {
                holder.tvProductDescription.setVisibility(View.GONE);
            }
            holder.tvProductDescription.setText(product.getProductDesc());
            if (product.getProductImage() == null || product.getProductImage().isEmpty()) {
                holder.cardImage.setVisibility(View.GONE);
            } else {
                holder.cardImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(product.getProductImage()).into(holder.ivProductImage);
            }

            if (product.isVeg()) {
                holder.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_veg));
            } else {
                holder.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_non_veg));
            }

            if (product.isInStock()) {
                holder.tvStockStatus.setText("In Stock");
                holder.tvStockStatus.setTextColor(context.getResources().getColor(R.color.color_text_title));
                holder.lhProduct.setBackgroundColor(context.getResources().getColor(R.color.white));
            } else {
                holder.tvStockStatus.setText("Out of Stock");
                holder.tvStockStatus.setTextColor(context.getResources().getColor(R.color.color_error));
                holder.lhProduct.setBackgroundColor(context.getResources().getColor(R.color.color_separator_light));
            }

            holder.lhProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("productId", product.getProductId());
                    context.startActivity(intent);
                }
            });

            holder.btnViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("productId", product.getProductId());
                    context.startActivity(intent);
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
        private TextView tvStockStatus;
        private LinearLayout lhProduct;
        private MaterialButton btnViewDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivServingType = itemView.findViewById(R.id.ivServingType);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            cardImage = itemView.findViewById(R.id.cardImage);
            tvStockStatus = itemView.findViewById(R.id.tvStockStatus);
            lhProduct = itemView.findViewById(R.id.lhProduct);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);

        }
    }
}
