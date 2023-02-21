package com.myfablo.seller.orders.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.orders.v2.Product;
import com.myfablo.seller.utils.ExtraUtils;

import java.util.List;

public class OrderItemRecyclerAdapter extends RecyclerView.Adapter<OrderItemRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;

    public OrderItemRecyclerAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OrderItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ordered_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemRecyclerAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product != null) {
            holder.tvProductName.setText(product.getProductName()+" (Qty x"+product.getQuantity()+")");
            holder.tvProductTotal.setText(String.format("%s%s", context.getString(R.string.str_rupee_symbol), product.getQuantityPrice()));
            if (product.getIsVeg()) {
                holder.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_veg));
            } else {
                holder.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_non_veg));
            }
            if (product.getVariationName() != null && !product.getVariationName().isEmpty()) {
                if (!product.getAddOnName().isEmpty()) {
                    holder.tvCustomization.setText(new ExtraUtils(context).getCustomName(product.getVariationName(), product.getAddOnName()));
                } else {
                    holder.tvCustomization.setText(product.getVariationName());
                }
                holder.tvCustomization.setVisibility(View.VISIBLE);
            } else {
                holder.tvCustomization.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivServingType;
        TextView tvProductName;
        TextView tvCustomization;
        TextView tvProductTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivServingType = itemView.findViewById(R.id.ivServingType);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCustomization = itemView.findViewById(R.id.tvCustomization);
            tvProductTotal = itemView.findViewById(R.id.tvProductTotal);
        }
    }
}
