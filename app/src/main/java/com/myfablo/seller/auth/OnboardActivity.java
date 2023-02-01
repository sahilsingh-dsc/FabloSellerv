package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityOnboardBinding;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.preference.OrderServicePref;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.preference.SelectedOptionPref;
import com.myfablo.seller.utils.alerts.LogoutAlert;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class OnboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOnboardBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OnboardActivity.this;
        initView();
    }

    private void initView() {
        initClickListener();
        generateAuthTokenQR();
    }

    private void initClickListener() {
        binding.btnLogout.setOnClickListener(this);
    }

    private void generateAuthTokenQR() {
        AuthPref authPref = new AuthPref(context);
        QRGEncoder qrgEncoder = new QRGEncoder(authPref.getAuthToken(), null, QRGContents.Type.TEXT, 500);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        Bitmap bitmap = qrgEncoder.getBitmap();
        binding.ivOnboardingQr.setImageBitmap(bitmap);
    }

    private void showLogoutAlert() {
        LogoutAlert logoutAlert = LogoutAlert.getInstance();
        logoutAlert.showAlert(context);
    }

    private void doLogout() {
        SelectedOptionPref selectedOptionPref = new SelectedOptionPref(context);
        OutletPref outletPref = new OutletPref(context);
        OrderServicePref orderServicePref = new OrderServicePref(context);
        AuthPref authPref = new AuthPref(context);

        selectedOptionPref.clearData();
        outletPref.clearData();
        orderServicePref.clearData();
        authPref.clearData();

        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("logout")) {
            doLogout();
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
    public void onClick(View view) {
        if (view == binding.btnLogout) {
            showLogoutAlert();
        }
    }
}