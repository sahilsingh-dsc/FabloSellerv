package com.myfablo.seller.home.outlets.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.myfablo.seller.R;
import com.myfablo.seller.home.outlets.OutletActivity;
import com.myfablo.seller.home.outlets.models.OutletItem;
import com.myfablo.seller.utils.alerts.OutletStatusAlert;
import com.suke.widget.SwitchButton;

import java.util.List;

public class OutletsRecyclerAdapter extends RecyclerView.Adapter<OutletsRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<OutletItem> outletItemList;

    public OutletsRecyclerAdapter(Context context, List<OutletItem> outletItemList) {
        this.context = context;
        this.outletItemList = outletItemList;
    }

    @NonNull
    @Override
    public OutletsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_outlet_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutletsRecyclerAdapter.ViewHolder holder, int position) {
        OutletItem outletItem = outletItemList.get(position);
        if (outletItem != null) {
            Glide.with(context).load(outletItem.getOutletImage().get(0)).into(holder.ivOutletImage);
            if (outletItem.getClosed()) {
                holder.ivOutletImage.setBackground(context.getResources().getDrawable(R.drawable.ring_outlet_offline));
                holder.lottieLive.setVisibility(View.GONE);
                holder.lottieLive.cancelAnimation();
                holder.lhOrderCount.setVisibility(View.GONE);
                holder.startOrderService.setChecked(false);
            } else {
                holder.ivOutletImage.setBackground(context.getResources().getDrawable(R.drawable.ring_outlet_online));
                holder.lottieLive.setVisibility(View.VISIBLE);
                holder.lottieLive.playAnimation();
                holder.lhOrderCount.setVisibility(View.VISIBLE);
                holder.startOrderService.setChecked(true);
            }
            holder.tvOutletName.setText(outletItem.getOutletName());
            holder.tvOutletArea.setText(outletItem.getArea());
            holder.tvDispatched.setText(outletItem.getDispatchedCount() + "");
            holder.tvPreparing.setText(outletItem.getPreperingCount() + "");
            holder.tvReady.setText(outletItem.getReadyCount() + "");

            holder.cardOutlet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OutletActivity.class);
                    intent.putExtra("outletId", outletItem.getOutletId());
                    intent.putExtra("outletName", outletItem.getOutletName());
                    intent.putExtra("outletArea", outletItem.getArea());
                    context.startActivity(intent);
                }
            });

            holder.viewNotificationAlert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OutletStatusAlert outletStatusAlert = OutletStatusAlert.getInstance();
                    outletStatusAlert.showAlert(context, outletItem.getClosed(), outletItem);
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return outletItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivOutletImage;
        private TextView tvOutletName;
        private TextView tvOutletArea;
        private TextView tvDispatched;
        private TextView tvPreparing;
        private TextView tvReady;
        private LinearLayout lvOutlet;
        private LinearLayout lhOrderCount;
        private LottieAnimationView lottieLive;
        private MaterialCardView cardOutlet;
        private SwitchButton startOrderService;
        private View viewNotificationAlert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivOutletImage = itemView.findViewById(R.id.ivOutletImage);
            tvOutletName = itemView.findViewById(R.id.tvOutletName);
            tvOutletArea = itemView.findViewById(R.id.tvOutletArea);
            tvDispatched = itemView.findViewById(R.id.tvDispatched);
            tvPreparing = itemView.findViewById(R.id.tvPreparing);
            tvReady = itemView.findViewById(R.id.tvReady);
            lvOutlet = itemView.findViewById(R.id.lvOutlet);
            lhOrderCount = itemView.findViewById(R.id.lhOrderCount);
            lottieLive = itemView.findViewById(R.id.lottieLive);
            cardOutlet = itemView.findViewById(R.id.cardOutlet);
            viewNotificationAlert = itemView.findViewById(R.id.viewNotificationAlert);
            startOrderService = itemView.findViewById(R.id.startOrderService);

        }
    }
}
