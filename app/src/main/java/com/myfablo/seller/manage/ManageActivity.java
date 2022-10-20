package com.myfablo.seller.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.auth.WelcomeActivity;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityManageBinding;
import com.myfablo.seller.interfaces.OutletInterface;
import com.myfablo.seller.manage.bank.BankActivity;
import com.myfablo.seller.manage.discount.DiscountPromotionActivity;
import com.myfablo.seller.manage.kyc.SellerKycActivity;
import com.myfablo.seller.manage.menu.MenuActivity;
import com.myfablo.seller.manage.orders.NewOrderBottomSheet;
import com.myfablo.seller.manage.orders.OrderHistoryActivity;
import com.myfablo.seller.manage.orders.PendingOrdersActivity;
import com.myfablo.seller.manage.outlet.OutletDetailsActivity;
import com.myfablo.seller.manage.payout.PayoutActivity;
import com.myfablo.seller.manage.support.SupportActivity;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.preference.OrderServicePref;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.preference.SelectedOptionPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.services.OrderService;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;
import com.myfablo.seller.utils.alerts.LogoutAlert;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener, SwitchButton.OnCheckedChangeListener {

    private ActivityManageBinding binding;
    private Context context;

    private FabLoading fabLoading;

    private static final String TAG = "ManageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = ManageActivity.this;
        initView();
    }

    private void initView() {
        fabLoading = FabLoading.getInstance();
        binding.ivGoBack.setOnClickListener(this);
        binding.lhOutletDetails.setOnClickListener(this);
        binding.lhBank.setOnClickListener(this);
        binding.lhMenu.setOnClickListener(this);
        binding.lhSellerKys.setOnClickListener(this);
        binding.lhDiscount.setOnClickListener(this);
        binding.lhPayout.setOnClickListener(this);
        binding.lhOrderHistory.setOnClickListener(this);
        binding.lhLogout.setOnClickListener(this);
        binding.lhHelpSupport.setOnClickListener(this);
        binding.switchOutletStatus.setOnCheckedChangeListener(this);
        getDefaultOutletData();
    }

    private void getDefaultOutletData() {
        OutletPref outletPref = new OutletPref(context);
        Glide.with(context).load(outletPref.getOutletImage()).into(binding.ivOutletImage);
        binding.tvOutletName.setText(outletPref.getOutletName());
        binding.tvOutletArea.setText(outletPref.getOutletArea());
        if (outletPref.getIsClosed()) {
            binding.switchOutletStatus.setChecked(false);
        } else {
            binding.switchOutletStatus.setChecked(true);
        }
    }

    private void updateOutletStatus() {
        fabLoading.showProgress(context);
        AuthPref authPref = new AuthPref(context);
        OutletPref outletPref = new OutletPref(context);
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<BasicResponse> call = outletInterface.updateOutletStatus("Bearer "+authPref.getAuthToken(), outletPref.getOutletId());
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            onBackPressed();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
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

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.lhOutletDetails) {
            Intent intent = new Intent(context, OutletDetailsActivity.class);
            startActivity(intent);
        } else if (view == binding.lhBank) {
            Intent intent = new Intent(context, BankActivity.class);
            startActivity(intent);
        } else if (view == binding.lhMenu) {
            Intent intent = new Intent(context, MenuActivity.class);
            startActivity(intent);
        } else if (view == binding.lhSellerKys) {
            Intent intent = new Intent(context, SellerKycActivity.class);
            startActivity(intent);
        } else if (view == binding.lhDiscount) {
            Intent intent = new Intent(context, DiscountPromotionActivity.class);
            startActivity(intent);
        } else if (view == binding.lhPayout) {
            Intent intent = new Intent(context, PayoutActivity.class);
            startActivity(intent);
        } else if (view == binding.lhLogout) {
            LogoutAlert logoutAlert = LogoutAlert.getInstance();
            logoutAlert.showAlert(context);
        } else if (view == binding.lhOrderHistory) {
            Intent intent = new Intent(context, OrderHistoryActivity.class);
            startActivity(intent);
        } else if (view == binding.lhHelpSupport) {
            Intent intent = new Intent(context, SupportActivity.class);
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PNMessageResult messageResult) {
        if (messageResult != null) {
            Intent intent = new Intent(context, PendingOrdersActivity.class);
            startActivity(intent);
        }
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
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        updateOutletStatus();
    }
}