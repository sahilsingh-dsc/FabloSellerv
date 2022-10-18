package com.myfablo.seller.home.outlets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityOutletBinding;
import com.myfablo.seller.home.outlets.fragments.OutletSelectionBottomSheet;
import com.myfablo.seller.home.outlets.models.single.OutletDetailsResponse;
import com.myfablo.seller.interfaces.OrdersInterface;
import com.myfablo.seller.interfaces.OutletInterface;
import com.myfablo.seller.manage.ManageActivity;
import com.myfablo.seller.manage.orders.PendingOrdersActivity;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.orders.OrderRecyclerAdapter;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.preference.SelectedOptionPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutletActivity extends AppCompatActivity implements View.OnClickListener, Animator.AnimatorListener {

    private ActivityOutletBinding binding;
    private Context context;
    private String noDataMessage;
    private FabLoading fabLoading;
    private SelectedOptionPref selectedOptionPref;

    private static final String TAG = "OutletActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutletBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OutletActivity.this;
        initView();
    }

    private void initView() {
        selectedOptionPref = new SelectedOptionPref(context);
        fabLoading = FabLoading.getInstance();
        binding.lottieRefresh.setOnClickListener(this);
        binding.lottieRefresh.addAnimatorListener(this);
        binding.tvPreparingOrders.setOnClickListener(this);
        binding.tvReadyOrders.setOnClickListener(this);
        binding.tvDispatchedOrders.setOnClickListener(this);
        binding.tvDeliveredOrders.setOnClickListener(this);
        binding.tvCancelledOrders.setOnClickListener(this);
        binding.ivOutletImage.setOnClickListener(this);

        binding.recyclerOrders.setLayoutManager(new LinearLayoutManager(context));
        binding.lottieRefresh.addAnimatorListener(this);
    }

    private void getOutletDetails(String outletId) {
        AuthPref authPref = new AuthPref(context);
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<OutletDetailsResponse> call = outletInterface.getSingleOutletDetails("Bearer "+authPref.getAuthToken(), outletId);
        call.enqueue(new Callback<OutletDetailsResponse>() {
            @Override
            public void onResponse(Call<OutletDetailsResponse> call, Response<OutletDetailsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OutletPref outletPref = new OutletPref(context);
                            outletPref.setDefaultOutletDetails(response.body().getItems());
                            if (response.body().getItems().getIsClosed()) {
                                binding.ivOutletImage.setBackground(getResources().getDrawable(R.drawable.ring_outlet_offline));
                            } else {
                                binding.ivOutletImage.setBackground(getResources().getDrawable(R.drawable.ring_outlet_online));
                            }
                            showSelectedOption();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutletDetailsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void getOrder(String orderStatus) {
        loadData();
        OutletPref outletPref = new OutletPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<OrderResponse> call = ordersInterface.getOrders(outletPref.getOutletId(), orderStatus);
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

    private void showSelectedOption() {
        if (selectedOptionPref.getSelectedOrderStatusOption().equals(Constant.SELECTED_ORDER_STATUS_OPTION_PREPARING)) {
            selectPreparingOrders();
        } else if (selectedOptionPref.getSelectedOrderStatusOption().equals(Constant.SELECTED_ORDER_STATUS_OPTION_READY)) {
            selectReadyOrders();
        } else if (selectedOptionPref.getSelectedOrderStatusOption().equals(Constant.SELECTED_ORDER_STATUS_OPTION_DISPATCHED)) {
            selectDispatchedOrders();
        } else if (selectedOptionPref.getSelectedOrderStatusOption().equals(Constant.SELECTED_ORDER_STATUS_OPTION_DELIVERED)) {
            selectDeliveredOrders();
        } else if (selectedOptionPref.getSelectedOrderStatusOption().equals(Constant.SELECTED_ORDER_STATUS_OPTION_CANCELLED)) {
            selectCancelledOrders();
        }
    }

    private void changeOrderStatus(OrderStatusChangeRequest orderStatusChangeRequest) {
        AuthPref authPref = new AuthPref(context);
        fabLoading.showProgress(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<BasicResponse> call = ordersInterface.changeOrderStatus(authPref.getAuthToken(), orderStatusChangeRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            selectPreparingOrders();
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

    private void selectPreparingOrders() {
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no preparing orders";

        getOrder(Constant.ORDER_STATUS_PREPARING);
        selectedOptionPref.setSelectedOrderStatusOption(Constant.SELECTED_ORDER_STATUS_OPTION_PREPARING);
    }

    private void selectReadyOrders() {
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no ready orders";

        getOrder(Constant.ORDER_STATUS_READY);
        selectedOptionPref.setSelectedOrderStatusOption(Constant.SELECTED_ORDER_STATUS_OPTION_READY);
    }

    private void selectDispatchedOrders() {
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no dispatched orders";

        getOrder(Constant.ORDER_STATUS_DISPATCHED);
        selectedOptionPref.setSelectedOrderStatusOption(Constant.SELECTED_ORDER_STATUS_OPTION_DISPATCHED);
    }

    private void selectDeliveredOrders() {
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no delivered orders";

        getOrder(Constant.ORDER_STATUS_DELIVERED);
        selectedOptionPref.setSelectedOrderStatusOption(Constant.SELECTED_ORDER_STATUS_OPTION_DELIVERED);
    }

    private void selectCancelledOrders() {
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no cancelled orders";

        getOrder(Constant.ORDER_STATUS_CANCELLED);
        selectedOptionPref.setSelectedOrderStatusOption(Constant.SELECTED_ORDER_STATUS_OPTION_CANCELLED);
    }

    private void setOutletData() {
        String outletId = getIntent().getStringExtra("outletId");
        String outletName = getIntent().getStringExtra("outletName");
        String outletArea = getIntent().getStringExtra("outletArea");
        getOutletDetails(outletId);
        binding.tvOutletName.setText(outletName);
        binding.tvOutletArea.setText(outletArea);
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
        binding.tvNoData.setText(noDataMessage);
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

    private void retryService() {
    }

    @Override
    public void onClick(View view) {
        if (view == binding.lhOutletSelector) {
            OutletSelectionBottomSheet outletSelectionBottomSheet = new OutletSelectionBottomSheet();
            outletSelectionBottomSheet.show(getSupportFragmentManager(), " outlets");
        } else if (view == binding.ivOutletImage) {
            Intent intent = new Intent(context, ManageActivity.class);
            startActivity(intent);
        } else if (view == binding.lottieRefresh) {
            binding.lottieRefresh.playAnimation();
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
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OrderStatusChangeRequest orderStatusChangeRequest) {
        if (orderStatusChangeRequest != null) {
            changeOrderStatus(orderStatusChangeRequest);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PNMessageResult messageResult) {
        if (messageResult != null) {
            Intent intent = new Intent(context, PendingOrdersActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onAnimationStart(Animator animator) {
        setOutletData();
    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

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