package com.myfablo.seller.home.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.BuildConfig;
import com.myfablo.seller.R;
import com.myfablo.seller.auth.WelcomeActivity;
import com.myfablo.seller.databinding.ActivitySellerAccountBinding;
import com.myfablo.seller.home.account.insights.SellerInsightsActivity;
import com.myfablo.seller.manage.bank.BankActivity;
import com.myfablo.seller.manage.discount.DiscountPromotionActivity;
import com.myfablo.seller.manage.kyc.SellerKycActivity;
import com.myfablo.seller.manage.payout.PayoutActivity;
import com.myfablo.seller.manage.support.SupportActivity;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.preference.OrderServicePref;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.preference.SelectedOptionPref;
import com.myfablo.seller.services.OrderService;
import com.myfablo.seller.utils.alerts.LogoutAlert;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SellerAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySellerAccountBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellerAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SellerAccountActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.lhLogout.setOnClickListener(this);
        binding.lhHelpSupport.setOnClickListener(this);
        binding.lhDiscount.setOnClickListener(this);
        binding.lhPayout.setOnClickListener(this);
        binding.lhSellerKys.setOnClickListener(this);
        binding.lhSellerInsights.setOnClickListener(this);
        binding.lhBank.setOnClickListener(this);

        getAppVersion();
    }

    private void doLogout() {
        SelectedOptionPref selectedOptionPref = new SelectedOptionPref(context);
        OutletPref outletPref = new OutletPref(context);
        OrderServicePref orderServicePref = new OrderServicePref(context);
        AuthPref authPref = new AuthPref(context);

        if (isMyServiceRunning(OrderService.class)) {
            stopOrderService();
        }

        selectedOptionPref.clearData();
        outletPref.clearData();
        orderServicePref.clearData();
        authPref.clearData();

        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void stopOrderService() {
        OutletPref outletPref = new OutletPref(getApplicationContext());
        Intent serviceIntent = new Intent(context, OrderService.class);
        serviceIntent.putExtra("sellerId", outletPref.getSellerId());
        serviceIntent.putExtra("status", "stop");
        ContextCompat.startForegroundService(context, serviceIntent);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void getAppVersion() {
        float versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        String buildVersionCode = getString(R.string.app_name)+" v"+versionCode+" & Build "+versionName;
        binding.tvAppVersion.setText(buildVersionCode);
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
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.lhHelpSupport) {
            Intent intent = new Intent(context, SupportActivity.class);
            startActivity(intent);
        } else if (view == binding.lhLogout) {
            LogoutAlert logoutAlert = LogoutAlert.getInstance();
            logoutAlert.showAlert(context);
        } else if (view == binding.lhPayout) {
            Intent intent = new Intent(context, PayoutActivity.class);
            startActivity(intent);
        } else if (view == binding.lhDiscount) {
            Intent intent = new Intent(context, DiscountPromotionActivity.class);
            startActivity(intent);
        } else if (view == binding.lhSellerKys) {
            Intent intent = new Intent(context, SellerKycActivity.class);
            startActivity(intent);
        } else if (view == binding.lhBank) {
            Intent intent = new Intent(context, BankActivity.class);
            startActivity(intent);
        } else if (view == binding.lhSellerInsights) {
            Intent intent = new Intent(context, SellerInsightsActivity.class);
            startActivity(intent);
        }
    }
}