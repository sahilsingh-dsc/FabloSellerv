package com.myfablo.seller.manage.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityOrderHistoryBinding;
import com.myfablo.seller.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOrderHistoryBinding binding;
    private Context context;

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

        binding.tvTodayOrders.setOnClickListener(this);
        binding.tvYesterdayOrders.setOnClickListener(this);
        binding.tvCustomDateOrders.setOnClickListener(this);

        selectTodayOrders();
    }

    private void getOrderHistory(String startDate, String endDate) {
        loadData();
        OutletPref outletPref = new OutletPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<OrderResponse> orderResponseCall = ordersInterface.getOrderByDate(outletPref.getOutletId(), Constant.ORDER_STATUS_READY, startDate, endDate);
        orderResponseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
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
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                showError();
            }
        });
    }

    private void selectTodayOrders() {
        binding.tvTodayOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvYesterdayOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCustomDateOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvTodayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvYesterdayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCustomDateOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        String endDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        String startDate = dateFormat.format(cal.getTime());

        getOrderHistory(startDate, endDate);
    }

    private void selectYesterdayOrders() {
        binding.tvYesterdayOrders.setTextColor(getResources().getColor(R.color.color_text_for_bg));
        binding.tvTodayOrders.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.tvCustomDateOrders.setTextColor(getResources().getColor(R.color.color_text_description));

        binding.tvYesterdayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_focused, null));
        binding.tvTodayOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));
        binding.tvCustomDateOrders.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select_un_focused, null));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String endDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        String startDate = dateFormat.format(cal.getTime());

        getOrderHistory(startDate, endDate);
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
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
                calendar.setTimeInMillis((Long) selection);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate  = format.format(calendar.getTime());
                Toast.makeText(context, ""+formattedDate, Toast.LENGTH_SHORT).show();
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
        }
    }
}