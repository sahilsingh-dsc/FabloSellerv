package com.myfablo.seller.manage.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfablo.seller.R;
import com.myfablo.seller.manage.orders.model.Item;
import com.myfablo.seller.orders.model.OrderItemRecyclerAdapter;
import com.myfablo.seller.utils.Constant;

import java.util.List;

public class OrderHistoryRecyclerAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> orderItems;

    public OrderHistoryRecyclerAdapter(Context context, List<Item> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderHistoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_all_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryRecyclerAdapter.ViewHolder holder, int position) {
        Item item = orderItems.get(position);
        if (item != null) {
            holder.tvOrderId.setText(item.getOrderId());

            holder.tvOrderTime.setText(item.getTiming().get(0).getTime());

            if (item.getStatus().equals(Constant.ORDER_STATUS_PENDING)) {
                holder.tvOrderStatus.setText("PENDING");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_pending));
            } else if (item.getStatus().equals(Constant.ORDER_STATUS_PREPARING)) {
                holder.tvOrderStatus.setText("PREPARING");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_preparing));
            } else if (item.getStatus().equals(Constant.ORDER_STATUS_READY)) {
                holder.tvOrderStatus.setText("READY");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_ready));
            } else if (item.getStatus().equals(Constant.ORDER_STATUS_DISPATCHED)) {
                holder.tvOrderStatus.setText("DISPATCHED");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_dispatched));
            } else if (item.getStatus().equals(Constant.ORDER_STATUS_DELIVERED)) {
                holder.tvOrderStatus.setText("DELIVERED");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_delivered));
            } else if (item.getStatus().equals(Constant.ORDER_STATUS_CANCELLED)) {
                holder.tvOrderStatus.setText("CANCELLED");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_cancelled));
            }

            holder.tvOrderTotal.setText(getPriceWithSymbol(item.getAmount().getTotalAmount()));
            holder.tvCustomerName.setText(item.getClient().getClientName());

            holder.recyclerOrderItem.setLayoutManager(new LinearLayoutManager(context));
            OrderItemRecyclerAdapter orderItemRecyclerAdapter = new OrderItemRecyclerAdapter(context, item.getProductList());
            holder.recyclerOrderItem.setAdapter(orderItemRecyclerAdapter);

        }
    }

    private String getPriceWithSymbol(int amount) {
        return "₹" + amount;
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderId;
        private TextView tvOrderStatus;
        private TextView tvOrderTime;
        private TextView tvCustomerName;
        private TextView tvOrderTotal;
        private RecyclerView recyclerOrderItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderTime = itemView.findViewById(R.id.tvOrderTime);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
            recyclerOrderItem = itemView.findViewById(R.id.recyclerOrderItem);

        }
    }
}
