package com.myfablo.seller.manage.menu.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.menu.MenuActivity;
import com.myfablo.seller.manage.menu.fragments.MenuToolBottomSheet;
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

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuToolBottomSheet bottomSheet = new MenuToolBottomSheet();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "subcategory");
                    bundle.putString("id", subCategory.getCategoryId());
                    bundle.putBoolean("hasSubCategory", subCategory.getHasSubCategory());
                    bottomSheet.setArguments(bundle);
                    bottomSheet.show(((MenuActivity) context).getSupportFragmentManager(), "menuTool");
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
        private RecyclerView recyclerProduct;
        private ImageView ivShowHideProducts;
        private MaterialButton btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSubCategoryName = itemView.findViewById(R.id.tvSubCategoryName);
            recyclerProduct = itemView.findViewById(R.id.recyclerProduct);
            ivShowHideProducts = itemView.findViewById(R.id.ivShowHideProducts);
            btnAdd = itemView.findViewById(R.id.btnAdd);

        }
    }
}
