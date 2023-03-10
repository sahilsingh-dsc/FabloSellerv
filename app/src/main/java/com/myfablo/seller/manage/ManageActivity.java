package com.myfablo.seller.manage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityManageBinding;
import com.myfablo.seller.utils.interfaces.OutletInterface;
import com.myfablo.seller.manage.discount.OutletDiscountActivity;
import com.myfablo.seller.manage.insights.OutletInsightsActivity;
import com.myfablo.seller.manage.menu.MenuActivity;
import com.myfablo.seller.manage.orders.OrderHistoryActivity;
import com.myfablo.seller.manage.orders.PendingOrdersActivity;
import com.myfablo.seller.manage.outlet.OutletDetailsActivity;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;
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
        binding.lhMenu.setOnClickListener(this);
        binding.lhOrderHistory.setOnClickListener(this);
        binding.lhDiscount.setOnClickListener(this);
        binding.lhOutletInsights.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.lhOutletDetails) {
            Intent intent = new Intent(context, OutletDetailsActivity.class);
            startActivity(intent);
        } else if (view == binding.lhMenu) {
            Intent intent = new Intent(context, MenuActivity.class);
            startActivity(intent);
        }  else if (view == binding.lhOrderHistory) {
            Intent intent = new Intent(context, OrderHistoryActivity.class);
            startActivity(intent);
        } else if (view == binding.lhDiscount) {
            Intent intent = new Intent(context, OutletDiscountActivity.class);
            startActivity(intent);
        } else if (view == binding.lhOutletInsights) {
            Intent intent = new Intent(context, OutletInsightsActivity.class);
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