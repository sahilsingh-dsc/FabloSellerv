package com.myfablo.seller.manage.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityPendingOrdersBinding;
import com.myfablo.seller.orders.v2.SellerOrdersResponse;
import com.myfablo.seller.utils.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.orders.OrderRecyclerAdapter;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingOrdersActivity extends AppCompatActivity {

    private ActivityPendingOrdersBinding binding;
    private Context context;
    private FabLoading fabLoading;

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
        fabLoading = FabLoading.getInstance();
        binding.recyclerOrders.setLayoutManager(new LinearLayoutManager(context));
        getOrder();
    }

    private void getOrder() {
        loadData();
        AuthPref authPref = new AuthPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<SellerOrdersResponse> call = ordersInterface.getOrderBySeller(authPref.getBearerToken());
        call.enqueue(new Callback<SellerOrdersResponse>() {
            @Override
            public void onResponse(Call<SellerOrdersResponse> call, Response<SellerOrdersResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOrders.setAdapter(orderRecyclerAdapter);
                            showData();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            noData();
                            onBackPressed();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SellerOrdersResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                showError();
            }
        });
    }

    private void changeOrderStatus(OrderStatusChangeRequest orderStatusChangeRequest) {
        AuthPref authPref = new AuthPref(context);
        fabLoading.showProgress(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<BasicResponse> call = ordersInterface.changeOrderStatus("Bearer " + authPref.getAuthToken(), orderStatusChangeRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            getOrder();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                fabLoading.hideProgress();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OrderStatusChangeRequest orderStatusChangeRequest) {
        if (orderStatusChangeRequest != null) {
            changeOrderStatus(orderStatusChangeRequest);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}