package com.myfablo.seller.manage.menu.variations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;

import java.util.List;

public class VariantRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Variant> variantList;

    public VariantRecyclerAdapter(Context context, List<Variant> variantList) {
        this.context = context;
        this.variantList = variantList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_variation_products, parent, false);
        return new VariantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Variant variant = variantList.get(position);
        if (variant != null) {
            ((VariantViewHolder) holder).bindData(context, variant);
        }
    }

    @Override
    public int getItemCount() {
        return variantList.size();
    }
}
