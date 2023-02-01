package com.myfablo.seller.manage.orders;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.databinding.BottomSheetNewOrderBinding;
import com.myfablo.seller.utils.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.Item;
import com.myfablo.seller.manage.orders.model.OrderDetailsResponse;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.orders.model.OrderItemRecyclerAdapter;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrderBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetNewOrderBinding binding;
    private Context context;
    private String orderId;

    private static final String TAG = "NewOrderBottomSheet";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetNewOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        binding.btnAccept.setOnClickListener(this);
        if (getArguments() != null) {
            orderId = getArguments().getString("orderId");
            getOrderDetails();
        }
        binding.recyclerOrderItem.setLayoutManager(new LinearLayoutManager(context));
    }

    private void getOrderDetails() {
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<OrderDetailsResponse> call = ordersInterface.getOrderDetails(orderId);
        call.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OrderDetailsResponse orderDetailsResponse = response.body();
                            Item item = orderDetailsResponse.getItems();
                            if (item != null) {
                                showOrderDetails(item);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void showOrderDetails(Item item) {
        binding.tvOrderId.setText(item.getOrderId());
        binding.tvOrderTime.setText(item.getTiming().get(0).getTime());
        binding.tvCustomerName.setText(item.getClient().getClientName());
        binding.tvOrderTotal.setText(getPriceWithSymbol(item.getPayableAmount()));
        OrderItemRecyclerAdapter orderItemRecyclerAdapter = new OrderItemRecyclerAdapter(context, item.getProductList());
        binding.recyclerOrderItem.setAdapter(orderItemRecyclerAdapter);
    }

    private String getPriceWithSymbol(float amount) {
        return "₹"+amount;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnAccept) {
            OrderStatusChangeRequest statusChangeRequest = new OrderStatusChangeRequest();
            statusChangeRequest.setOrderId(orderId);
            statusChangeRequest.setOrderStatus(Constant.ORDER_STATUS_ACCEPTED);
            EventBus.getDefault().post(statusChangeRequest);
            dismiss();
        } else if (view == binding.btnReject) {
            OrderStatusChangeRequest statusChangeRequest = new OrderStatusChangeRequest();
            statusChangeRequest.setOrderId(orderId);
            statusChangeRequest.setOrderStatus(Constant.ORDER_STATUS_CANCELLED);
            EventBus.getDefault().post(statusChangeRequest);
            dismiss();
        }
    }
}
