package com.myfablo.seller.manage.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.models.Customization;

import java.util.List;

public class FoodCustomizationRecyclerAdapter extends RecyclerView.Adapter<FoodCustomizationRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Customization> customizationList;
    private String name;
    private float price;
    private String productId;

    public FoodCustomizationRecyclerAdapter(Context context, List<Customization> customizationList, String name, float price, String productId) {
        this.context = context;
        this.customizationList = customizationList;
        this.name = name;
        this.price = price;
        this.productId = productId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_customization, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customization customization = customizationList.get(position);
        if (customization != null) {
            holder.tvCustomization.setText(customization.getCustomizationName());
            holder.recyclerCustomItem.setLayoutManager(new LinearLayoutManager(context));
            FoodCustomItemRecyclerAdapter customItemRecyclerAdapter = new FoodCustomItemRecyclerAdapter(context, customization.getCustomItem(), name, price, productId);
            holder.recyclerCustomItem.setAdapter(customItemRecyclerAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return customizationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCustomization;
        private RecyclerView recyclerCustomItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCustomization = itemView.findViewById(R.id.tvCustomization);
            recyclerCustomItem = itemView.findViewById(R.id.recyclerCustomItem);

        }
    }
}
