package com.myfablo.seller.catalogue.product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.product.model.Datum;
import com.myfablo.seller.utils.Constant;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Datum> productsList;

    public ProductRecyclerAdapter(Context context, List<Datum> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ViewHolder holder, int position) {
        Datum datum = productsList.get(position);
        if (datum != null) {

            Glide.with(context).load(datum.getImageUrl()).placeholder(R.drawable.ic_placeholder).into(holder.ivProductImage);
            holder.tvProductName.setText(datum.getName());
            holder.tvProductPrice.setText("₹"+datum.getPrice());

            if (datum.getIsEnabled() == Constant.STATUS_TRUE) {
                holder.tvProductStatus.setText(R.string.str_status_active);
                holder.tvProductStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_active, null));
            } else {
                holder.tvProductStatus.setText(R.string.str_status_inactive);
                holder.tvProductStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_inactive, null));
            }

            if (datum.getCustomization() != null) {
                if (datum.getCustomization().size() > 0) {
                    holder.tvCustomization.setVisibility(View.VISIBLE);
                } else {
                    holder.tvCustomization.setVisibility(View.GONE);
                }
            } else {
                holder.tvCustomization.setVisibility(View.GONE);
            }

            if (datum.getIsVeg() != null) {
                holder.ivFoodType.setVisibility(View.VISIBLE);
                if (datum.getIsVeg() == Constant.STATUS_FALSE) {
                    holder.ivFoodType.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_food_type_non_veg, null));
                } else if (datum.getIsVeg() == Constant.STATUS_TRUE) {
                    holder.ivFoodType.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_food_type_veg, null));
                }
            } else {
                holder.ivFoodType.setVisibility(View.GONE);
            }

            holder.cardProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product", datum);
                    if (datum.getCustomization() != null) {
                        if (datum.getCustomization().size() > 0) {
                            intent.putExtra("custom", Constant.STATUS_TRUE);
                        } else {
                            intent.putExtra("custom", Constant.STATUS_FALSE);
                        }
                    } else {
                        intent.putExtra("custom", Constant.STATUS_FALSE);
                    }
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardProduct;
        private ImageView ivProductImage;
        private ImageView ivFoodType;
        private TextView tvProductName;
        private TextView tvProductPrice;
        private TextView tvProductStatus;
        private TextView tvCustomization;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardProduct = itemView.findViewById(R.id.cardProduct);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            ivFoodType = itemView.findViewById(R.id.ivFoodType);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductStatus = itemView.findViewById(R.id.tvProductStatus);
            tvCustomization = itemView.findViewById(R.id.tvCustomization);

        }
    }
}
