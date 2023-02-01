package com.myfablo.seller.manage.support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.databinding.ActivitySupportBinding;
import com.myfablo.seller.utils.interfaces.SellerInterface;
import com.myfablo.seller.manage.support.models.manager.SupportManagerResponse;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySupportBinding binding;
    private Context context;

    private static final String TAG = "SupportActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SupportActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        getSupportManagerDetails();
    }

    private void getSupportManagerDetails() {
        AuthPref authPref = new AuthPref(context);
        SellerInterface sellerInterface = RestClient.getRetrofitFabloUserService(context).create(SellerInterface.class);
        Call<SupportManagerResponse> call = sellerInterface.getSupportManager(authPref.getBearerToken());
        call.enqueue(new Callback<SupportManagerResponse>() {
            @Override
            public void onResponse(Call<SupportManagerResponse> call, Response<SupportManagerResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            setManagerDetails(response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SupportManagerResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void setManagerDetails(SupportManagerResponse managerDetails) {
        binding.tvDmName.setText(managerDetails.getItems().getName());
        binding.tvDmPhone.setText(managerDetails.getItems().getPhone());
        binding.tvDmEmail.setText(managerDetails.getItems().getEmail());
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}