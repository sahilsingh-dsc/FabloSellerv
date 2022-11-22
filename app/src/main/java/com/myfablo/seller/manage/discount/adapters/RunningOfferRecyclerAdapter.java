package com.myfablo.seller.manage.discount.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.discount.models.running.Item;

import java.util.List;

public class RunningOfferRecyclerAdapter extends RecyclerView.Adapter<RunningOfferRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> offerItemList;

    public RunningOfferRecyclerAdapter(Context context, List<Item> offerItemList) {
        this.context = context;
        this.offerItemList = offerItemList;
    }

    @NonNull
    @Override
    public RunningOfferRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_offers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RunningOfferRecyclerAdapter.ViewHolder holder, int position) {
        Item offerItems = offerItemList.get(position);
        if (offerItems != null) {
            holder.tvOfferPercent.setText(offerItems.getDiscountPercent()+"% OFF");
            if (offerItems.getIsFlatDiscount()) {
                holder.tvOfferDescription.setText("Customers will get "+offerItems.getDiscountPercent()+"% off on all orders above ₹"+offerItems.getMinAmount()+" with no upper limit");
            } else {
                holder.tvOfferDescription.setText("Customers will get "+offerItems.getDiscountPercent()+"% off on all order above ₹"+offerItems.getMinAmount()+" Maximum discount: ₹"+offerItems.getMaxDiscount());
            }

        }
    }

    @Override
    public int getItemCount() {
        return offerItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOfferPercent;
        private TextView tvOfferStatus;
        private TextView tvOfferDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOfferPercent = itemView.findViewById(R.id.tvOfferPercent);
            tvOfferStatus = itemView.findViewById(R.id.tvOfferStatus);
            tvOfferDescription = itemView.findViewById(R.id.tvOfferDescription);

        }
    }
}
