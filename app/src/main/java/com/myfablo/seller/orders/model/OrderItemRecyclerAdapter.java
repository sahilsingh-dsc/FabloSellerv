package com.myfablo.seller.orders.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.orders.model.Product;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemRecyclerAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product != null) {
//            if (product.getHasCustomization()) {
//                holder.tvItemName.setText(product.getProductName()+" ("+product.getCustomization().get(0).getCustomItemName()+")");
//            } else {
//                holder.tvItemName.setText(product.getProductName());
//            }
            holder.tvItemQuantity.setText(product.getQuantity()+"");

        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemQuantity;
        private TextView tvItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemQuantity = itemView.findViewById(R.id.tvItemQuantity);
            tvItemName = itemView.findViewById(R.id.tvItemName);

        }
    }
}
