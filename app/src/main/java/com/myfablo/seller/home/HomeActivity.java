package com.myfablo.seller.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityHomeBinding;
import com.myfablo.seller.home.outlets.adapters.OutletsRecyclerAdapter;
import com.myfablo.seller.home.outlets.models.OutletItem;
import com.myfablo.seller.home.outlets.models.OutletsResponse;
import com.myfablo.seller.interfaces.OrdersInterface;
import com.myfablo.seller.interfaces.OutletInterface;
import com.myfablo.seller.manage.orders.NewOrderBottomSheet;
import com.myfablo.seller.manage.orders.PendingOrdersActivity;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.orders.OrderRecyclerAdapter;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.preference.OrderServicePref;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.preference.SelectedOptionPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.services.OrderService;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.OrderNotificationAlert;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements SwitchButton.OnCheckedChangeListener, View.OnClickListener {

    private ActivityHomeBinding binding;
    private Context context;
    private SelectedOptionPref selectedOptionPref;

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = HomeActivity.this;
        initView();
    }

    private void initView() {
        selectedOptionPref = new SelectedOptionPref(context);
        binding.recyclerOutlets.setLayoutManager(new LinearLayoutManager(context));
        binding.startOrderService.setOnCheckedChangeListener(this);
        binding.lvOfflineOutlet.setOnClickListener(this);
        binding.lvOnlineOutlet.setOnClickListener(this);
        binding.viewNotificationAlert.setOnClickListener(this);
        binding.cardPendingOrders.setOnClickListener(this);
    }

    private void initOrderService() {
        OrderServicePref orderServicePref = new OrderServicePref(context);
        if (orderServicePref.getServiceStatus()) {
            if (!isMyServiceRunning(OrderService.class)) {
                startOrderService();
            }
            binding.startOrderService.setChecked(true);
        } else {
            binding.startOrderService.setChecked(false);
        }

        if (selectedOptionPref.getSelectedOutletOption().equals(Constant.SELECTED_OUTLET_OPTION_ONLINE)) {
            showOnlineOutlets();
        } else if (selectedOptionPref.getSelectedOutletOption().equals(Constant.SELECTED_OUTLET_OPTION_OFFLINE)) {
            showOfflineOutlets();
        }
    }

    private void getAllOutlets(int outletStatus) {
        loadData();
        AuthPref authPref = new AuthPref(context);
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<OutletsResponse> call = outletInterface.getOutletsByStatus("Bearer " + authPref.getAuthToken(), outletStatus);
        call.enqueue(new Callback<OutletsResponse>() {
            @Override
            public void onResponse(Call<OutletsResponse> call, Response<OutletsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OutletsRecyclerAdapter outletsRecyclerAdapter = new OutletsRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOutlets.setAdapter(outletsRecyclerAdapter);
                            showData();
                        } else {
                            noData(outletStatus);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutletsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                showError();
            }
        });
    }

    private void startOrderService() {
        OutletPref outletPref = new OutletPref(getApplicationContext());
        Intent serviceIntent = new Intent(context, OrderService.class);
        serviceIntent.putExtra("sellerId", outletPref.getSellerId());
        serviceIntent.putExtra("status", "start");
        ContextCompat.startForegroundService(context, serviceIntent);
    }

    private void stopOrderService() {
        OutletPref outletPref = new OutletPref(getApplicationContext());
        Intent serviceIntent = new Intent(context, OrderService.class);
        serviceIntent.putExtra("sellerId", outletPref.getSellerId());
        serviceIntent.putExtra("status", "stop");
        ContextCompat.startForegroundService(context, serviceIntent);
    }

    private void loadData() {
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lvServiceOffline.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData(int status) {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lvServiceOffline.setVisibility(View.GONE);
        binding.lottieNoData.playAnimation();
        if (status == Constant.OUTLET_STATUS_MODE_ONLINE) {
            binding.tvNoData.setText("There are no online outlets");
        } else if (status == Constant.OUTLET_STATUS_MODE_OFFLINE) {
            binding.tvNoData.setText("There are no offline outlets");
        }
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerOutlets.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lvServiceOffline.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lvServiceOffline.setVisibility(View.GONE);
        binding.lottieError.playAnimation();
    }

    private void showOnlineOutlets() {
        binding.viewOnlineOutlet.setVisibility(View.VISIBLE);
        binding.viewOfflineOutlet.setVisibility(View.INVISIBLE);
        binding.tvOnlineOutlet.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvOfflineOutlet.setTextColor(getResources().getColor(R.color.color_text_description));
        getAllOutlets(Constant.OUTLET_STATUS_MODE_ONLINE);
        selectedOptionPref.setSelectedOutletOption(Constant.SELECTED_OUTLET_OPTION_ONLINE);
    }

    private void showOfflineOutlets() {
        binding.viewOnlineOutlet.setVisibility(View.INVISIBLE);
        binding.viewOfflineOutlet.setVisibility(View.VISIBLE);
        binding.tvOfflineOutlet.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvOnlineOutlet.setTextColor(getResources().getColor(R.color.color_text_description));
        getAllOutlets(Constant.OUTLET_STATUS_MODE_OFFLINE);
        selectedOptionPref.setSelectedOutletOption(Constant.SELECTED_OUTLET_OPTION_OFFLINE);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void updateOutletStatus(OutletItem outletItem) {
        AuthPref authPref = new AuthPref(context);
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<BasicResponse> call = outletInterface.updateOutletStatus("Bearer "+authPref.getAuthToken(), outletItem.getOutletId());
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            if (selectedOptionPref.getSelectedOutletOption().equals(Constant.SELECTED_OUTLET_OPTION_ONLINE)) {
                                showOnlineOutlets();
                            } else if (selectedOptionPref.getSelectedOutletOption().equals(Constant.SELECTED_OUTLET_OPTION_OFFLINE)) {
                                showOfflineOutlets();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void getOrder() {
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<OrderResponse> call = ordersInterface.getOrders("cfc7876424a1", Constant.ORDER_STATUS_PENDING);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            binding.cardPendingOrders.setVisibility(View.VISIBLE);
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            binding.cardPendingOrders.setVisibility(View.GONE);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PNMessageResult messageResult) {
        if (messageResult != null) {
            Intent intent = new Intent(context, PendingOrdersActivity.class);
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OutletItem outletItem) {
        if (outletItem != null) {
            updateOutletStatus(outletItem);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("orderNotification")) {
            OrderServicePref orderServicePref = new OrderServicePref(context);
            if (orderServicePref.getServiceStatus()) {
                binding.startOrderService.setChecked(false);
            } else {
                binding.startOrderService.setChecked(true);
            }
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

    @Override
    protected void onResume() {
        super.onResume();
        initOrderService();
        getOrder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        OrderServicePref orderServicePref = new OrderServicePref(context);
        orderServicePref.setServiceStatus(isChecked);
        if (isChecked) {
            startOrderService();
        } else {
            stopOrderService();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.lvOfflineOutlet) {
            showOfflineOutlets();
        } else if (view == binding.lvOnlineOutlet) {
            showOnlineOutlets();
        } else if (view == binding.viewNotificationAlert) {
            OrderServicePref orderServicePref = new OrderServicePref(context);
            OrderNotificationAlert orderNotificationAlert = OrderNotificationAlert.getInstance();
            orderNotificationAlert.showAlert(context, orderServicePref.getServiceStatus());
        } else if (view == binding.cardPendingOrders) {
            Intent intent = new Intent(context, PendingOrdersActivity.class);
            startActivity(intent);
        }
    }
}