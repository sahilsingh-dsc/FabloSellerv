package com.myfablo.seller.manage.orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.databinding.ActivityPendingOrdersBinding;
import com.myfablo.seller.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.orders.OrderRecyclerAdapter;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingOrdersActivity extends AppCompatActivity {

    private ActivityPendingOrdersBinding binding;
    private Context context;

    private static final String TAG = "PendingOrdersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendingOrdersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = PendingOrdersActivity.this;
        initView();
    }

    private void initView() {
        getOrder();
    }

    private void getOrder() {
        loadData();
        OutletPref outletPref = new OutletPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<OrderResponse> call = ordersInterface.getOrders("cfc7876424a1", Constant.ORDER_STATUS_PENDING);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOrders.setAdapter(orderRecyclerAdapter);
                            showData();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            noData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                showError();
            }
        });
    }

    private void loadData() {
        binding.recyclerOrders.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerOrders.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerOrders.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerOrders.setVisibility(View.GONE);
        binding.lottieNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
    }

}