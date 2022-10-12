package com.myfablo.seller.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfablo.seller.auth.model.UserLoginRequest;
import com.myfablo.seller.auth.model.UserLoginResponse;
import com.myfablo.seller.databinding.ActivityAuthBinding;
import com.myfablo.seller.home.HomeActivity;
import com.myfablo.seller.interfaces.AuthInterface;
import com.myfablo.seller.main.MainActivity;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

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
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        fabLoading.showProgress(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabLoading.hideProgress();
                AuthPref authPref = new AuthPref(context);
                if (!authPref.getAuthToken().equals("none")) {
                    gotoHomeScreen();
                }
            }
        }, 1500);

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
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void gotoVerifyOtpScreen(String reqId, String phone, Boolean isOnboarded) {
        Intent intent = new Intent(context, OtpActivity.class);
        intent.putExtra(UserLoginResponse.RES_AUTH_REQUEST_ID, reqId);
        intent.putExtra(UserLoginResponse.RES_AUTH_PHONE, phone);
        intent.putExtra(UserLoginResponse.RES_AUTH_ONBOARD, isOnboarded);
        startActivity(intent);
    }

    private void gotoInitScreen() {
        Intent intent = new Intent(context, InitActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoHomeScreen() {
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void H() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void validateInput() {
        String phone = binding.etPhone.getText().toString();
        if (phone.length() == 10 && TextUtils.isDigitsOnly(phone)) {
            doSellerLogin(phone);
        } else {
            Toast.makeText(context, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSendOtp) {
            validateInput();
        }
    }
}