package com.myfablo.seller.home.account.insights;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.myfablo.seller.databinding.ActivitySellerInsightsBinding;
import com.myfablo.seller.home.account.insights.models.SellerProfitResponse;
import com.myfablo.seller.utils.interfaces.OrdersInterface;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerInsightsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySellerInsightsBinding binding;
    private Context context;
    private String selectedStartDate = "";
    private String selectedEndDate = "";

    private static final String TAG = "SellerInsightsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellerInsightsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SellerInsightsActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.ivIncrementDate.setOnClickListener(this);
        binding.ivDecrementDate.setOnClickListener(this);
        binding.tvSelectedDate.setOnClickListener(this);
        binding.tvSelectedDate.setText(getTodayDate());
        getTodayDate();
        getSellerProfit();
    }

    private String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        String startDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String endDate = dateFormat.format(cal.getTime());
        selectedStartDate = startDate;
        selectedEndDate = endDate;
        return startDate;
    }

    private void getSellerProfit() {
        binding.tvOrderCount.setText("...");
        binding.tvTotalSale.setText("...");
        binding.tvTotalDiscounts.setText("...");
        binding.tvNetProfit.setText("...");
        AuthPref authPref = new AuthPref(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<SellerProfitResponse> call = ordersInterface.getSellerProfit(authPref.getBearerToken(), selectedStartDate, selectedEndDate);
        call.enqueue(new Callback<SellerProfitResponse>() {
            @Override
            public void onResponse(Call<SellerProfitResponse> call, Response<SellerProfitResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            binding.tvTotalSale.setText("₹"+response.body().getItems().getTotalSale());
                            binding.tvOrderCount.setText(""+response.body().getItems().getOrderCount());
                            binding.tvTotalDiscounts.setText("₹"+response.body().getItems().getTotalDiscount());
                            binding.tvNetProfit.setText("₹"+response.body().getItems().getNetProfit());
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {

                        }
                     }
                }
            }

            @Override
            public void onFailure(Call<SellerProfitResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.tvSelectedDate) {
            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
            materialDateBuilder.setTitleText("Select Start Date");
            final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    String dateRange = materialDatePicker.getHeaderText();
                    String[] dateRangeSplit = dateRange.split(" – ");
                    selectedStartDate = dateRangeSplit[0]+" 2022";
                    selectedEndDate = dateRangeSplit[1]+" 2022";
                    binding.tvSelectedDate.setText(materialDatePicker.getHeaderText());
                    getSellerProfit();
                }
            });
        }
     }

}