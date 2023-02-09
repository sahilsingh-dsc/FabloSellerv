package com.myfablo.seller.orders;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.myfablo.seller.R;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.orders.model.OrderItemRecyclerAdapter;
import com.myfablo.seller.orders.v2.Item;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.RejectOrderAlert;
import com.ncorti.slidetoact.SlideToActView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Item> orderItems;

    private static final String TAG = "OrderRecyclerAdapter";

    public OrderRecyclerAdapter(Context context, List<Item> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {
        Item item = orderItems.get(position);
        if (item != null) {
            holder.tvOrderId.setText(item.getOrderId());

            holder.tvOrderTime.setText(item.getTiming().get(0).getDate()+", "+item.getTiming().get(0).getTime());

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
            } else if (item.getTiming().get(item.getTiming().size()-1).getStatus().equals("preparing") && item.getStatus().equals(Constant.ORDER_STATUS_ASSIGNED)) {
                holder.tvOrderStatus.setText("PREPARING");
                holder.tvOrderStatus.setBackground(context.getResources().getDrawable(R.drawable.bg_status_preparing));
            }

            if (item.getStatus().equals(Constant.ORDER_STATUS_PENDING)) {
                holder.lhAcceptRejectOrder.setVisibility(View.VISIBLE);
            } else {
                holder.lhAcceptRejectOrder.setVisibility(View.GONE);
            }

            if (item.getStatus().equals(Constant.ORDER_STATUS_PREPARING) ) {
                holder.sliderOrderReady.setVisibility(View.VISIBLE);
            } else {
                if (item.getTiming().get(item.getTiming().size()-1).getStatus().equals("preparing") && item.getStatus().equals(Constant.ORDER_STATUS_ASSIGNED)) {
                    holder.sliderOrderReady.setVisibility(View.VISIBLE);
                } else {
                    holder.sliderOrderReady.setVisibility(View.GONE);
                }
            }


            if (item.getStatus().equals(Constant.ORDER_STATUS_PENDING)) {
                holder.lhOutletSelector.setVisibility(View.VISIBLE);
                holder.tvOutletName.setText(item.getOutlet().getOutletName());
                holder.tvOutletArea.setText(item.getOutlet().getOutletArea());
            } else {
                holder.lhOutletSelector.setVisibility(View.GONE);
            }

            holder.tvOrderTotal.setText(getPriceWithSymbol(item.getAmount().getTotalAmount()));
            holder.tvCustomerName.setText(item.getClient().getClientName());

            holder.recyclerOrderItem.setLayoutManager(new LinearLayoutManager(context));
            OrderItemRecyclerAdapter orderItemRecyclerAdapter = new OrderItemRecyclerAdapter(context, item.getProductList());
            holder.recyclerOrderItem.setAdapter(orderItemRecyclerAdapter);

            holder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OrderStatusChangeRequest orderStatusChangeRequest = new OrderStatusChangeRequest();
                    orderStatusChangeRequest.setOrderId(item.getOrderId());
                    orderStatusChangeRequest.setOrderStatus(Constant.ORDER_STATUS_ACCEPTED);
                    EventBus.getDefault().post(orderStatusChangeRequest);
                }
            });

            holder.btnReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RejectOrderAlert rejectOrderAlert = RejectOrderAlert.getInstance();
                    rejectOrderAlert.showAlert(context, item.getOrderId());
                }
            });

//            holder.btnOrderReady.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    OrderStatusChangeRequest orderStatusChangeRequest = new OrderStatusChangeRequest();
//                    orderStatusChangeRequest.setOrderId(item.getOrderId());
//                    orderStatusChangeRequest.setOrderStatus(Constant.ORDER_STATUS_READY);
//                    EventBus.getDefault().post(orderStatusChangeRequest);
//                    return true;
//                }
//            });

            holder.sliderOrderReady.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
                @Override
                public void onSlideComplete(@NonNull SlideToActView slideToActView) {
                    OrderStatusChangeRequest orderStatusChangeRequest = new OrderStatusChangeRequest();
                    orderStatusChangeRequest.setOrderId(item.getOrderId());
                    orderStatusChangeRequest.setOrderStatus(Constant.ORDER_STATUS_READY);
                    EventBus.getDefault().post(orderStatusChangeRequest);
                }
            });

        }
    }

    private String getPriceWithSymbol(Float amount) {
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
        private TextView tvOutletName;
        private TextView tvOutletArea;
        private RecyclerView recyclerOrderItem;
        private MaterialButton btnReject;
        private MaterialButton btnAccept;
        private MaterialButton btnOrderReady;
        private LinearLayout lhAcceptRejectOrder;
        private LinearLayout lhOutletSelector;
        private SlideToActView sliderOrderReady;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderTime = itemView.findViewById(R.id.tvOrderTime);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
            tvOutletName = itemView.findViewById(R.id.tvOutletName);
            tvOutletArea = itemView.findViewById(R.id.tvOutletArea);
            recyclerOrderItem = itemView.findViewById(R.id.recyclerOrderItem);
            btnReject = itemView.findViewById(R.id.btnReject);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            sliderOrderReady = itemView.findViewById(R.id.sliderOrderReady);
            lhAcceptRejectOrder = itemView.findViewById(R.id.lhAcceptRejectOrder);
            lhOutletSelector = itemView.findViewById(R.id.lhOutletSelector);

        }
    }
}
