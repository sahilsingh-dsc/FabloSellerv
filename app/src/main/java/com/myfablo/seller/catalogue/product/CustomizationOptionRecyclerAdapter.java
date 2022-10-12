package com.myfablo.seller.catalogue.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.customization.model.ProductCustomizationOption;

import java.util.List;

public class CustomizationOptionRecyclerAdapter extends RecyclerView.Adapter<CustomizationOptionRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ProductCustomizationOption> productCustomizationOptionList;

    public CustomizationOptionRecyclerAdapter(Context context, List<ProductCustomizationOption> productCustomizationOptionList) {
        this.context = context;
        this.productCustomizationOptionList = productCustomizationOptionList;
    }

    @NonNull
    @Override
    public CustomizationOptionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customization_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomizationOptionRecyclerAdapter.ViewHolder holder, int position) {
        ProductCustomizationOption productCustomizationOption = productCustomizationOptionList.get(position);
        if (productCustomizationOption != null) {
            holder.tvName.setText(productCustomizationOption.getName());
            holder.tvPrice.setText("₹"+productCustomizationOption.getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return productCustomizationOptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
