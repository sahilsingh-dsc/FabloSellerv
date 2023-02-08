package com.myfablo.seller.manage.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityOrderHistoryBinding;
import com.myfablo.seller.orders.v2.SellerOrdersResponse;
import com.myfablo.seller.utils.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOrderHistoryBinding binding;
    private Context context;
    private String noDataMessage;
    private String startDate;
    private String endDate;
    private String orderStatus;
    private static final String TAG = "OrderHistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OrderHistoryActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.recyclerOrders.setLayoutManager(new LinearLayoutManager(context));
        binding.tvPreparingOrders.setOnClickListener(this);
        binding.tvAllOrders.setOnClickListener(this);
        binding.tvReadyOrders.setOnClickListener(this);
        binding.tvDispatchedOrders.setOnClickListener(this);
        binding.tvDeliveredOrders.setOnClickListener(this);
        binding.tvCancelledOrders.setOnClickListener(this);
        binding.tvTodayOrders.setOnClickListener(this);
        binding.tvYesterdayOrders.setOnClickListener(this);
        binding.tvCustomDateOrders.setOnClickListener(this);

        selectAllOrders();
        selectTodayOrders();
    }

    private void getOrderHistory() {
        loadData();
        OutletPref outletPref = new OutletPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<SellerOrdersResponse> orderResponseCall = ordersInterface.getOrderByDate(outletPref.getOutletId(), orderStatus, startDate, endDate);
        orderResponseCall.enqueue(new Callback<SellerOrdersResponse>() {
            @Override
            public void onResponse(Call<SellerOrdersResponse> call, Response<SellerOrdersResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OrderHistoryRecyclerAdapter orderHistoryRecyclerAdapter = new OrderHistoryRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOrders.setAdapter(orderHistoryRecyclerAdapter);
                            showData();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            noData();
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

    private void getT() {

    }

    private void selectAllOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no orders";
        orderStatus = Constant.ORDER_STATUS_ALL;
        getOrderHistory();
    }

    private void selectPreparingOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no preparing orders";
        orderStatus = Constant.ORDER_STATUS_PREPARING;
        getOrderHistory();
    }

    private void selectReadyOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no ready orders";
        orderStatus = Constant.ORDER_STATUS_READY;
        getOrderHistory();
    }

    private void selectDispatchedOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no dispatched orders";
        orderStatus = Constant.ORDER_STATUS_DISPATCHED;
        getOrderHistory();
    }

    private void selectDeliveredOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no delivered orders";
        orderStatus = Constant.ORDER_STATUS_DELIVERED;
        getOrderHistory();
    }

    private void selectCancelledOrders() {
        binding.tvAllOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCancelledOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvReadyOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvPreparingOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDispatchedOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvDeliveredOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvAllOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCancelledOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvReadyOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvPreparingOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDispatchedOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvDeliveredOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        noDataMessage = "You have no cancelled orders";
        orderStatus = Constant.ORDER_STATUS_CANCELLED;
        getOrderHistory();
    }

    private void selectTodayOrders() {
        binding.tvCustomDateOrders.setText("Date Range");
        binding.tvTodayOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvYesterdayOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCustomDateOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvTodayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvYesterdayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCustomDateOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        startDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        endDate = dateFormat.format(cal.getTime());

        getOrderHistory();
    }

    private void selectYesterdayOrders() {
        binding.tvCustomDateOrders.setText("Date Range");
        binding.tvYesterdayOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvTodayOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCustomDateOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvYesterdayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvTodayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCustomDateOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        endDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        startDate = dateFormat.format(cal.getTime());

        getOrderHistory();
    }

    private void selectCustomDate() {
        binding.tvCustomDateOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvYesterdayOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvTodayOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvCustomDateOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvYesterdayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvTodayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("Select Start Date");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String dateRange = materialDatePicker.getHeaderText();
                String[] dateRangeSplit = dateRange.split(" – ");
                startDate = dateRangeSplit[0] + " 2022";
                endDate = dateRangeSplit[1] + " 2022";
                binding.tvCustomDateOrders.setText(materialDatePicker.getHeaderText());
                getOrderHistory();
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

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.tvTodayOrders) {
            selectTodayOrders();
        } else if (view == binding.tvYesterdayOrders) {
            selectYesterdayOrders();
        } else if (view == binding.tvCustomDateOrders) {
            selectCustomDate();
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
        } else if (view == binding.tvAllOrders) {
            selectAllOrders();
        }
    }
}