package com.myfablo.seller.home.outlets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityAllOutletsBinding;
import com.myfablo.seller.home.outlets.adapters.OutletsRecyclerAdapter;
import com.myfablo.seller.home.outlets.models.OutletsResponse;
import com.myfablo.seller.utils.interfaces.OutletInterface;
import com.myfablo.seller.manage.orders.NewOrderBottomSheet;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOutletsActivity extends AppCompatActivity {

    private ActivityAllOutletsBinding binding;
    private Context context;

    private static final String TAG = "AllOutletsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllOutletsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = AllOutletsActivity.this;
        initView();
    }

    private void initView() {
        binding.recyclerOutlets.setLayoutManager(new LinearLayoutManager(context));
    }

    private void getAllOutlets() {
        loadData();
        AuthPref authPref = new AuthPref(context);
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<OutletsResponse> call = outletInterface.getOutletsByStatus("Bearer " + authPref.getAuthToken(), Constant.OUTLET_STATUS_MODE_OFFLINE);
        call.enqueue(new Callback<OutletsResponse>() {
            @Override
            public void onResponse(Call<OutletsResponse> call, Response<OutletsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OutletsRecyclerAdapter outletsRecyclerAdapter = new OutletsRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOutlets.setAdapter(outletsRecyclerAdapter);
                            showData();
                        } else {
                            noData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutletsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                showError();
            }
        });
    }

    private void loadData() {
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerOutlets.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerOutlets.setVisibility(View.GONE);
        binding.lottieNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PNMessageResult messageResult) {
        if (messageResult != null) {
            NewOrderBottomSheet newOrderBottomSheet = new NewOrderBottomSheet();
            newOrderBottomSheet.setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenBottomSheet);
            newOrderBottomSheet.setCancelable(false);
            Bundle bundle = new Bundle();
            bundle.putString("orderId", messageResult.getMessage().getAsString());
            newOrderBottomSheet.setArguments(bundle);
            newOrderBottomSheet.show(getSupportFragmentManager(), "newOrder");
            getAllOutlets();
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
    protected void onResume() {
        super.onResume();
        getAllOutlets();
    }
}