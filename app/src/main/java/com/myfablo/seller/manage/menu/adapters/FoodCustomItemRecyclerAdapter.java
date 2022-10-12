package com.myfablo.seller.manage.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.models.CustomItem;

import java.util.List;

public class FoodCustomItemRecyclerAdapter extends RecyclerView.Adapter<FoodCustomItemRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<CustomItem> customItemList;
    private String name;
    private float price;
    private String productId;

    int selectedPosition = 0;

    public FoodCustomItemRecyclerAdapter(Context context, List<CustomItem> customItemList, String name, float price, String productId) {
        this.context = context;
        this.customItemList = customItemList;
        this.name = name;
        this.price = price;
        this.productId = productId;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_custom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomItem customItem = customItemList.get(position);
        if (customItem != null) {
            holder.tvItemName.setText(customItem.getCustomItemName());
            holder.tvItemPrice.setText("₹"+customItem.getCustomItemPrice());
        }
    }

    @Override
    public int getItemCount() {
        return customItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivServingType;
        private TextView tvItemName;
        private TextView tvItemPrice;
        private ImageView ivSelect;
        private LinearLayout lhCustomItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivServingType = itemView.findViewById(R.id.ivServingType);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            lhCustomItem = itemView.findViewById(R.id.lhCustomItem);

        }
    }
}
