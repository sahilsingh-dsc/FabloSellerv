package com.myfablo.seller.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.CatalogueActivity;
import com.myfablo.seller.databinding.ActivityMainBinding;
import com.myfablo.seller.interfaces.MpInterface;
import com.myfablo.seller.interfaces.TaskInterface;
import com.myfablo.seller.orders.model.AcceptRejectOrderRequest;
import com.myfablo.seller.orders.model.AcceptRejectOrderResponse;
import com.myfablo.seller.orders.model.AllOrderResponseDataDetails;
import com.myfablo.seller.orders.model.MpOrderStatusUpdateRequest;
import com.myfablo.seller.orders.model.UpdateOrderStatusRequest;
import com.myfablo.seller.orders.model.UpdateOrderStatusResponse;
import com.myfablo.seller.preference.SellerPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private Context context;
    private FabLoading fabLoading;
    private List<AllOrderResponseDataDetails> allOrderResponseDataDetailsList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = MainActivity.this;
        initView();
    }

    private void initView() {
        fabLoading = FabLoading.getInstance();
        binding.recyclerOrder.setLayoutManager(new LinearLayoutManager(context));

        binding.tvPendingOrders.setOnClickListener(this);
        binding.tvPreparingOrders.setOnClickListener(this);
        binding.tvReadyOrders.setOnClickListener(this);
        binding.tvDispatchedOrders.setOnClickListener(this);
        binding.tvDeliveredOrders.setOnClickListener(this);
        binding.tvCancelledOrders.setOnClickListener(this);

        binding.ivAccount.setOnClickListener(this);

        allOrderResponseDataDetailsList = new ArrayList<>();
        showSellerData();
    }

    private void showSellerData() {
        SellerPref sellerPref = new SellerPref(context);
    }

//    private void fetchAllOrders(int status) {
//        loadData();
//        SellerPref sellerPref = new SellerPref(context);
//        AllOrderRequest allOrderRequest = new AllOrderRequest();
//        allOrderRequest.setApiKey(Constant.YELO_API_KEY);
//        allOrderRequest.setMarketplaceUserId(Constant.MP_USER_ID);
//        allOrderRequest.setUserId(sellerPref.getUserId());
//        allOrderRequest.setOrderStatus(status);
//        allOrderRequest.setSortCol(Constant.ORDER_SORT_COL_BY_DELIVERY_DATE_TIME);
//        allOrderRequest.setSortDir(Constant.ORDER_SORT_DIR_DESC);
//        allOrderRequest.setStart(0);
//        allOrderRequest.setLength(100);
//
//        DateTimeZone timeZone = DateTimeZone.forID("Etc/UCT");
//        DateTime now = DateTime.now( timeZone );
//        DateTime todayStart = now.withTimeAtStartOfDay();
//        DateTime yesterdayStart = now.minusDays(1).withTimeAtStartOfDay();
//
//        String[] todayDateSplit = todayStart.toString().split("T");
//        String[] yesterdayDateSplit = yesterdayStart.toString().split("T");
//        String startDateTime = yesterdayDateSplit[0]+"T18:30";
//        String endDateTime = todayDateSplit[0]+"T18:29";
//
//        allOrderRequest.setStartDate(startDateTime);
//        allOrderRequest.setEndDate(endDateTime);
//
//        OrdersInterface ordersInterface = RestClient.getRetrofitYeloInstance(context).create(OrdersInterface.class);
//        Call<AllOrderResponse> orderResponseCall = ordersInterface.getAllOrders(allOrderRequest);
//        orderResponseCall.enqueue(new Callback<AllOrderResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<AllOrderResponse> call, @NonNull Response<AllOrderResponse> response) {
//                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
//                    if (response.body() != null) {
//                        if (response.body().getStatus() == Constant.HTTP_RESPONSE_SUCCESS) {
//                            allOrderResponseDataDetailsList.clear();
//                            if (response.body().getData() != null) {
//                                allOrderResponseDataDetailsList = response.body().getData().getAllJobs();
//                                if (allOrderResponseDataDetailsList.size() > 0) {
//                                    OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(context, allOrderResponseDataDetailsList);
//                                    binding.recyclerOrder.setAdapter(orderRecyclerAdapter);
//                                    binding.recyclerOrder.setVisibility(View.VISIBLE);
//                                    showData();
//                                } else {
//                                    noData();
//                                }
//                            } else {
//                                noData();
//                            }
//                        } else {
//                            noData();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<AllOrderResponse> call, @NonNull Throwable t) {
//                noData();
//                Log.e(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//    }

    private void acceptRejectOrder(AcceptRejectOrderRequest acceptRejectOrderRequest) {
        fabLoading.showProgress(context);
        TaskInterface taskInterface = RestClient.getRetrofitYeloInstance(context).create(TaskInterface.class);
        Call<AcceptRejectOrderResponse> acceptRejectOrderResponseCall = taskInterface.acceptRejectOrder(acceptRejectOrderRequest);
        acceptRejectOrderResponseCall.enqueue(new Callback<AcceptRejectOrderResponse>() {
            @Override
            public void onResponse(Call<AcceptRejectOrderResponse> call, Response<AcceptRejectOrderResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        selectPendingOrders();
                    }
                }
            }

            @Override
            public void onFailure(Call<AcceptRejectOrderResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        fabLoading.showProgress(context);
        TaskInterface taskInterface = RestClient.getRetrofitYeloInstance(context).create(TaskInterface.class);
        Call<UpdateOrderStatusResponse> updateOrderStatusResponseCall = taskInterface.updateOrderStatus(updateOrderStatusRequest);
        updateOrderStatusResponseCall.enqueue(new Callback<UpdateOrderStatusResponse>() {
            @Override
            public void onResponse(Call<UpdateOrderStatusResponse> call, Response<UpdateOrderStatusResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        selectPreparingOrders();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateOrderStatusResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void mpUpdateOrderStatus(MpOrderStatusUpdateRequest mpOrderStatusUpdateRequest) {
        fabLoading.showProgress(context);
        MpInterface mpInterface = RestClient.getRetrofitMpInstance(context).create(MpInterface.class);
        Call<UpdateOrderStatusResponse> call = mpInterface.updateOrderStatus(mpOrderStatusUpdateRequest);
        call.enqueue(new Callback<UpdateOrderStatusResponse>() {
            @Override
            public void onResponse(Call<UpdateOrderStatusResponse> call, Response<UpdateOrderStatusResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        selectPreparingOrders();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateOrderStatusResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void selectPendingOrders() {
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_PENDING);
    }

    private void selectPreparingOrders() {
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_PREPARING);
    }

    private void selectReadyOrders() {
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_READY);
    }

    private void selectDispatchedOrders() {
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_DISPATCHED);
    }

    private void selectDeliveredOrders() {
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_DELIVERED);
    }

    private void selectCancelledOrders() {
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPendingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPendingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

//        fetchAllOrders(Constant.ORDER_STATUS_CANCELLED_REQUEST);
    }

    private void loadData() {
        binding.recyclerOrder.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.VISIBLE);
    }

    private void showData() {
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerOrder.setVisibility(View.VISIBLE);
    }

    private void noData() {
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerOrder.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
         if (view == binding.tvPendingOrders) {
            selectPendingOrders();
        } else if (view == binding.tvPreparingOrders) {
            selectPreparingOrders();
        } else if (view == binding.tvReadyOrders) {
             selectReadyOrders();
         } else if (view == binding.tvDispatchedOrders) {
            selectDispatchedOrders();
        } else if (view == binding.tvDeliveredOrders) {
            selectDeliveredOrders();
        } else if (view == binding.tvCancelledOrders) {
            selectCancelledOrders();
        } else if (view == binding.ivAccount) {
             Intent intent = new Intent(context, CatalogueActivity.class);
             startActivity(intent);
         }
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectPendingOrders();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AcceptRejectOrderRequest acceptRejectOrderRequest) {
        if (acceptRejectOrderRequest != null) {
            acceptRejectOrder(acceptRejectOrderRequest);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdateOrderStatusRequest updateOrderStatusRequest) {
        if (updateOrderStatusRequest != null) {
            updateOrderStatus(updateOrderStatusRequest);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MpOrderStatusUpdateRequest mpOrderStatusUpdateRequest) {
        if (mpOrderStatusUpdateRequest != null) {
            mpUpdateOrderStatus(mpOrderStatusUpdateRequest);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}