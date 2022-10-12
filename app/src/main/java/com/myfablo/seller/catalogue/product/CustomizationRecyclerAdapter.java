package com.myfablo.seller.catalogue.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.customization.model.ProductCustomization;

import java.util.List;

public class CustomizationRecyclerAdapter extends RecyclerView.Adapter<CustomizationRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ProductCustomization> productCustomizationList;

    public CustomizationRecyclerAdapter(Context context, List<ProductCustomization> productCustomizationList) {
        this.context = context;
        this.productCustomizationList = productCustomizationList;
    }

    @NonNull
    @Override
    public CustomizationRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customization, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomizationRecyclerAdapter.ViewHolder holder, int position) {
        ProductCustomization productCustomization = productCustomizationList.get(position);
        if (productCustomization != null) {
            holder.tvOptionName.setText(productCustomization.getName());
            holder.recyclerCustomizationOption.setLayoutManager(new LinearLayoutManager(context));
            CustomizationOptionRecyclerAdapter customizationOptionRecyclerAdapter = new CustomizationOptionRecyclerAdapter(context, productCustomization.getCustomizeOptions());
            holder.recyclerCustomizationOption.setAdapter(customizationOptionRecyclerAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return productCustomizationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOptionName;
        private RecyclerView recyclerCustomizationOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOptionName = itemView.findViewById(R.id.tvOptionName);
            recyclerCustomizationOption = itemView.findViewById(R.id.recyclerCustomizationOption);
        }
    }
}
