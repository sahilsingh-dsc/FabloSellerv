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
import com.myfablo.seller.manage.menu.models.Menu;

import java.util.List;

public class FoodMenuCategoryRecyclerAdapter extends RecyclerView.Adapter<FoodMenuCategoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Menu> menuList;

    public FoodMenuCategoryRecyclerAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        if (menu != null) {
            holder.tvCategoryName.setText(menu.getCategoryName()+" ("+menu.getItemCount()+")");
            if (menu.getHasSubCategory()) {
                holder.recyclerSubCategory.setLayoutManager(new LinearLayoutManager(context));
                FoodMenuSubCategoryRecyclerAdapter foodMenuSubCategoryRecyclerAdapter = new FoodMenuSubCategoryRecyclerAdapter(context,
                        menu.getSubCategoryList());
                holder.recyclerSubCategory.setAdapter(foodMenuSubCategoryRecyclerAdapter);
                holder.ivShowHideProducts.setVisibility(View.GONE);
            } else {
                holder.recyclerProduct.setLayoutManager(new LinearLayoutManager(context));
                FoodMenuProductRecyclerAdapter foodMenuProductRecyclerAdapter = new FoodMenuProductRecyclerAdapter(context, menu.getProductList());
                holder.recyclerProduct.setAdapter(foodMenuProductRecyclerAdapter);
                holder.ivShowHideProducts.setVisibility(View.VISIBLE);
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
            }

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuToolBottomSheet bottomSheet = new MenuToolBottomSheet();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "category");
                    bundle.putString("id", menu.getCategoryId());
                    bundle.putBoolean("hasSubCategory", menu.getHasSubCategory());
                    bottomSheet.setArguments(bundle);
                    bottomSheet.show(((MenuActivity) context).getSupportFragmentManager(), "menuTool");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCategoryName;
        private RecyclerView recyclerSubCategory;
        private RecyclerView recyclerProduct;
        private ImageView ivShowHideProducts;
        private MaterialButton btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            recyclerSubCategory = itemView.findViewById(R.id.recyclerSubCategory);
            recyclerProduct = itemView.findViewById(R.id.recyclerProduct);
            ivShowHideProducts = itemView.findViewById(R.id.ivShowHideProducts);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}
