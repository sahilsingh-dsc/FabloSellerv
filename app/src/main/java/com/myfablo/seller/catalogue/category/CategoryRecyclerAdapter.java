package com.myfablo.seller.catalogue.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.category.model.Result;
import com.myfablo.seller.utils.Constant;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Result> categoryDataList;

    public CategoryRecyclerAdapter(Context context, List<Result> categoryDataList) {
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ViewHolder holder, int position) {
        Result result = categoryDataList.get(position);
        if (result != null) {
            holder.tvCategoryName.setText(result.getName());
            holder.tvCategoryDescription.setText(result.getDescription());
            Glide.with(context).load(result.getImageUrl()).placeholder(R.drawable.ic_placeholder).into(holder.ivCategoryImage);

            if (result.getIsEnabled() == Constant.STATUS_TRUE) {
                holder.tvCategoryStatus.setText(R.string.str_status_active);
                holder.tvCategoryStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_active, null));
            } else {
                holder.tvCategoryStatus.setText(R.string.str_status_inactive);
                holder.tvCategoryStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_inactive, null));
            }

            holder.cardCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CategoryDetailsActivity.class);
                    intent.putExtra("category", result);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardCategory;
        private ImageView ivCategoryImage;
        private TextView tvCategoryName;
        private TextView tvCategoryDescription;
        private TextView tvCategoryStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardCategory = itemView.findViewById(R.id.cardCategory);
            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryDescription = itemView.findViewById(R.id.tvCategoryDescription);
            tvCategoryStatus = itemView.findViewById(R.id.tvCategoryStatus);

        }
    }
}
