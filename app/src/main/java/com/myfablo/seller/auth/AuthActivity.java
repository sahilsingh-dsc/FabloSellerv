package com.myfablo.seller.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.myfablo.seller.auth.models.UserLoginRequest;
import com.myfablo.seller.auth.models.UserLoginResponse;
import com.myfablo.seller.databinding.ActivityAuthBinding;
import com.myfablo.seller.utils.interfaces.AuthInterface;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private ActivityAuthBinding binding;
    private Context context;
    private FabLoading fabLoading;

    private static final String TAG = "AuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = AuthActivity.this;
        initView();
    }

    private void initView() {
        fabLoading = FabLoading.getInstance();
        binding.btnSendOtp.setOnClickListener(this);
        binding.etPhone.addTextChangedListener(this);
        getLoginType();
    }

    private void getLoginType() {
        String type = getIntent().getStringExtra("type");
        if (type.equals(Constant.LOGIN_TYPE_OUTLET)) {
            binding.tvLoginTitle.setText("Outlet Login");
        } else if (type.equals(Constant.LOGIN_TYPE_SELLER)) {
            binding.tvLoginTitle.setText("Seller Login");
        }
    }

    private void doSellerLogin(String phone) {
        fabLoading.showProgress(context);
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUserType(Constant.FABLO_USER_TYPE_SELLER);
        userLoginRequest.setPhone(phone);


        AuthInterface authInterface = RestClient.getRetrofitFabloUserService(context).create(AuthInterface.class);
        Call<UserLoginResponse> call = authInterface.userLogin(userLoginRequest);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            gotoVerifyOtpScreen(response.body().getItems().getReqId(), phone, response.body().getItems().getIsOnboarded());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void gotoVerifyOtpScreen(String reqId, String phone, Boolean isOnboarded) {
        AuthPref authPref = new AuthPref(context);
        authPref.setOnboardStatus(isOnboarded);
        Intent intent = new Intent(context, OtpActivity.class);
        intent.putExtra(UserLoginResponse.RES_AUTH_REQUEST_ID, reqId);
        intent.putExtra(UserLoginResponse.RES_AUTH_PHONE, phone);
        intent.putExtra(UserLoginResponse.RES_AUTH_ONBOARD, isOnboarded);
        startActivity(intent);
    }

    private void validateInput() {
        String phone = binding.etPhone.getText().toString();
        if (phone.length() == 10 && TextUtils.isDigitsOnly(phone)) {
            doSellerLogin(phone);
        } else {
            binding.tvPhoneError.setText("Please provide valid phone number");
            binding.tvPhoneError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSendOtp) {
            validateInput();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        binding.tvPhoneError.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}