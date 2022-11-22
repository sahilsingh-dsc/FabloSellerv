package com.myfablo.seller.manage.discount.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.databinding.BottomSheetDiscountSelectorBinding;
import com.myfablo.seller.interfaces.DiscountInterface;
import com.myfablo.seller.manage.discount.adapters.SelectOfferRecyclerAdapter;
import com.myfablo.seller.manage.discount.models.running.Item;
import com.myfablo.seller.manage.discount.models.running.RunningOffersResponse;
import com.myfablo.seller.manage.discount.models.running.SelectOffer;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountSelectorBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetDiscountSelectorBinding binding;
    private Context context;
    private Item selectedOfferItem;

    private static final String TAG = "DiscountSelectorBottom";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetDiscountSelectorBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        selectedOfferItem = new Item();
        binding.btnSelectDiscount.setOnClickListener(this);
        binding.recyclerDiscounts.setLayoutManager(new LinearLayoutManager(context));
        getRunningOffers();
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
                            SelectOfferRecyclerAdapter offerRecyclerAdapter = new SelectOfferRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerDiscounts.setAdapter(offerRecyclerAdapter);
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
        binding.recyclerDiscounts.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerDiscounts.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieNoData.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerDiscounts.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieLoading.cancelAnimation();
    }

    private void showError() {
        binding.recyclerDiscounts.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Item offerItem) {
        if (offerItem != null) {
            selectedOfferItem = offerItem;
            binding.btnSelectDiscount.setEnabled(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSelectDiscount) {
            OutletPref outletPref = new OutletPref(context);
            SelectOffer selectOffer = new SelectOffer();
            selectOffer.setDiscountId(selectedOfferItem.getDiscountId());
            selectOffer.setOutletId(outletPref.getOutletId());
            EventBus.getDefault().post(selectOffer);
            dismiss();
        }
    }
}
