package com.myfablo.seller.catalogue.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.category.model.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<SubCategoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Result> categoryDataList;
    private int row_index = 0;

    public SubCategoryRecyclerAdapter(Context context, List<Result> categoryDataList) {
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public SubCategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryRecyclerAdapter.ViewHolder holder, int position) {
        Result result = categoryDataList.get(position);
        if (result != null) {
            holder.tvSubCategory.setText(result.getName());
            holder.tvSubCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

            if (row_index == holder.getAdapterPosition()) {
                EventBus.getDefault().post(result);
                holder.tvSubCategory.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_select_focused, null));
                holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.color_text_for_bg));
            } else {
                holder.tvSubCategory.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_select_un_focused, null));
                holder.tvSubCategory.setTextColor(context.getResources().getColor(R.color.color_text_description));
            }

        }
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSubCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubCategory = itemView.findViewById(R.id.tvSubCategory);
        }
    }
}
