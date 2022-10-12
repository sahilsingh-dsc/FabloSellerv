package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.auth.model.VerifyOtpRequest;
import com.myfablo.seller.auth.model.VerifyOtpResponse;
import com.myfablo.seller.databinding.ActivityOtpBinding;
import com.myfablo.seller.interfaces.AuthInterface;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOtpBinding binding;
    private Context context;

    private FabLoading fabLoading;

    private static final String TAG = "OtpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OtpActivity.this;
        initView();
    }

    private void initView() {
        fabLoading = FabLoading.getInstance();
        binding.btnVerify.setOnClickListener(this);

        String phone = getIntent().getStringExtra("phone");
        binding.tvPhoneOtpTitle.setText("Enter OTP sent to "+phone);
    }

    private void doVerifyOtp(String otp) {
        fabLoading.showProgress(context);
        String reqId = getIntent().getStringExtra("reqId");
        boolean onboard = getIntent().getBooleanExtra("onboard", false);
        VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
        verifyOtpRequest.setReqId(reqId);
        verifyOtpRequest.setOtp(otp);
        AuthInterface authInterface = RestClient.getRetrofitFabloUserService(context).create(AuthInterface.class);
        Call<VerifyOtpResponse> call = authInterface.verifyOtp(verifyOtpRequest);
        call.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            AuthPref authPref = new AuthPref(context);
                            authPref.setUser(response.body().getItems());
                            if (onboard) {
                                gotoInitScreen();
                            } else {
                                gotoOnboardScreen();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                fabLoading.hideProgress();
            }
        });
    }

    private void validateInput() {
        String otp = binding.etOtp.getText().toString();
        if (otp.length() == 4 && TextUtils.isDigitsOnly(otp)) {
            doVerifyOtp(otp);
        }
    }

    private void gotoInitScreen() {
        Intent intent = new Intent(context, InitActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoOnboardScreen() {
        Intent intent = new Intent(context, OnboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnVerify) {
            validateInput();
        }
    }
}