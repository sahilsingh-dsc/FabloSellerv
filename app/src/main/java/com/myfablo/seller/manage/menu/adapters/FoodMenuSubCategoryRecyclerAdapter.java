package com.myfablo.seller.manage.menu.adapters;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.MenuActivity;
import com.myfablo.seller.manage.menu.add.AddProductActivity;
import com.myfablo.seller.manage.menu.add.fragments.AddProductBottomSheet;
import com.myfablo.seller.manage.menu.models.SubCategory;

import java.util.List;

public class FoodMenuSubCategoryRecyclerAdapter extends RecyclerView.Adapter<FoodMenuSubCategoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<SubCategory> subCategoryList;

    public FoodMenuSubCategoryRecyclerAdapter(Context context, List<SubCategory> subCategoryList) {
        this.context = context;
        this.subCategoryList = subCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_subcategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);
        if (subCategory != null) {
            holder.tvSubCategoryName.setText(subCategory.getCategoryName()+" ("+subCategory.getItemCount()+")");
            holder.recyclerProduct.setLayoutManager(new LinearLayoutManager(context));
            FoodMenuProductRecyclerAdapter foodMenuProductRecyclerAdapter = new FoodMenuProductRecyclerAdapter(context, subCategory.getProductList());
            holder.recyclerProduct.setAdapter(foodMenuProductRecyclerAdapter);
            holder.ivShowHideProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.recyclerProduct.getVisibility() == View.VISIBLE) {
                        holder.recyclerProduct.setVisibility(View.GONE);
                        holder.ivShowHideProducts.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_arrow_down));
                    } else {
                        holder.recyclerProduct.setVisibility(View.VISIBLE);
                        holder.ivShowHideProducts.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_arrow_up));
                    }
                }
            });

            holder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddProductActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSubCategoryName;
        private TextView tvAdd;
        private RecyclerView recyclerProduct;
        private ImageView ivShowHideProducts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSubCategoryName = itemView.findViewById(R.id.tvSubCategoryName);
            tvAdd = itemView.findViewById(R.id.tvAdd);
            recyclerProduct = itemView.findViewById(R.id.recyclerProduct);
            ivShowHideProducts = itemView.findViewById(R.id.ivShowHideProducts);

        }
    }
}
