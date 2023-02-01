package com.myfablo.seller.manage.discount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityDiscountPromotionBinding;
import com.myfablo.seller.utils.interfaces.DiscountInterface;
import com.myfablo.seller.manage.discount.adapters.RunningOfferRecyclerAdapter;
import com.myfablo.seller.manage.discount.models.running.RunningOffersResponse;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
DiscountPromotionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDiscountPromotionBinding binding;
    private Context context;

    private static final String TAG = "DiscountPromotion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscountPromotionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = DiscountPromotionActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardCampaign.setOnClickListener(this);
        binding.cardManageCampaign.setOnClickListener(this);
        binding.lvCreateOffer.setOnClickListener(this);
        binding.lvRunningOffer.setOnClickListener(this);
        binding.cardDiscountCap.setOnClickListener(this);
        binding.cardDiscountFlat.setOnClickListener(this);
        binding.recyclerRunningOffers.setLayoutManager(new LinearLayoutManager(context));
        selectCreateOffer();
    }

    private void selectCreateOffer() {
        binding.viewCreateOffer.setVisibility(View.VISIBLE);
        binding.viewRunningOffer.setVisibility(View.INVISIBLE);
        binding.tvCreateOffer.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvRunningOffer.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.scrollCreate.setVisibility(View.VISIBLE);
        binding.frameRunningOffer.setVisibility(View.GONE);
    }

    private void selectRunningOffer() {
        binding.viewRunningOffer.setVisibility(View.VISIBLE);
        binding.viewCreateOffer.setVisibility(View.INVISIBLE);
        binding.tvRunningOffer.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvCreateOffer.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.scrollCreate.setVisibility(View.GONE);
        binding.frameRunningOffer.setVisibility(View.VISIBLE);
        getRunningOffers();
    }

    private void gotoCreateDiscountScreen(String type) {
        Intent intent = new Intent(context, CreateDiscountActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void getRunningOffers() {
        loadData();
        AuthPref authPref = new AuthPref(context);
        DiscountInterface discountInterface = RestClient.getRetrofitFabloInventoryService(context).create(DiscountInterface.class);
        Call<RunningOffersResponse> call = discountInterface.getRunningOffers(authPref.getBearerToken());
        call.enqueue(new Callback<RunningOffersResponse>() {
            @Override
            public void onResponse(Call<RunningOffersResponse> call, Response<RunningOffersResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            RunningOfferRecyclerAdapter offerRecyclerAdapter = new RunningOfferRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerRunningOffers.setAdapter(offerRecyclerAdapter);
                            showData();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            noData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RunningOffersResponse> call, Throwable t) {
                showError();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void loadData() {
        binding.recyclerRunningOffers.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerRunningOffers.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieNoData.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerRunningOffers.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieLoading.cancelAnimation();
    }

    private void showError() {
        binding.recyclerRunningOffers.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.cardDiscountCap) {
            gotoCreateDiscountScreen("cap");
        } else if (view == binding.cardDiscountFlat) {
            gotoCreateDiscountScreen("flat");
        } else if (view == binding.lvCreateOffer) {
            selectCreateOffer();
        } else if (view == binding.lvRunningOffer) {
            selectRunningOffer();
        } else if (view == binding.cardCampaign) {
            OhSnapErrorAlert snapErrorAlert = OhSnapErrorAlert.getInstance();
            snapErrorAlert.showAlert(context, "In order to create campaign please contact your dedicated manager.");
        } else if (view == binding.cardManageCampaign) {
            OhSnapErrorAlert snapErrorAlert = OhSnapErrorAlert.getInstance();
            snapErrorAlert.showAlert(context, "In order to manage campaign please contact your dedicated manager.");
        }
    }
}