package com.myfablo.seller.manage.menu.addons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;

import java.util.List;

public class AddonsCategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Item> addonsCategoryList;

    public AddonsCategoryRecyclerAdapter(Context context, List<Item> addonsCategoryList) {
        this.context = context;
        this.addonsCategoryList = addonsCategoryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_addon_category, parent, false);
        return new AddonsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item addonsCategory = addonsCategoryList.get(position);
        if (addonsCategory != null) {
            ((AddonsCategoryViewHolder) holder).bindData(context, addonsCategory);
        }
    }

    @Override
    public int getItemCount() {
        return addonsCategoryList.size();
    }
}
