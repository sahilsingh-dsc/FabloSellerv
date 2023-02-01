package com.myfablo.seller.manage.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityOutletDiscountBinding;
import com.myfablo.seller.utils.interfaces.DiscountInterface;
import com.myfablo.seller.manage.discount.fragments.DiscountSelectorBottomSheet;
import com.myfablo.seller.manage.discount.models.outlet.OutletOfferResponse;
import com.myfablo.seller.manage.discount.models.running.SelectOffer;
import com.myfablo.seller.manage.discount.models.running.SelectOfferRequest;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.SuccessAlert;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutletDiscountActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOutletDiscountBinding binding;
    private Context context;
    private SelectOffer selectedOffer;

    private static final String TAG = "OutletDiscountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutletDiscountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OutletDiscountActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        selectedOffer = new SelectOffer();
        binding.btnSelectDiscount.setOnClickListener(this);
    }

    private void getOutletOffer() {
        loadData();
        AuthPref authPref = new AuthPref(context);
        OutletPref outletPref = new OutletPref(context);
        DiscountInterface discountInterface = RestClient.getRetrofitFabloInventoryService(context).create(DiscountInterface.class);
        Call<OutletOfferResponse> call = discountInterface.getOutletOffer(authPref.getBearerToken(), outletPref.getOutletId());
        call.enqueue(new Callback<OutletOfferResponse>() {
            @Override
            public void onResponse(Call<OutletOfferResponse> call, Response<OutletOfferResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            binding.tvOfferPercent.setText(response.body().getItems().getDiscountPercent()+"% OFF");
                            if (response.body().getItems().getIsFlatDiscount()) {
                                binding.tvOfferDescription.setText("Customers will get "+response.body().getItems().getDiscountPercent()+"% off on all orders above ₹"+response.body().getItems().getMinAmount()+" with no upper limit");
                            } else {
                                binding.tvOfferDescription.setText("Customers will get "+response.body().getItems().getDiscountPercent()+"% off on all order above ₹"+response.body().getItems().getMinAmount()+" Maximum discount: ₹"+response.body().getItems().getMaxDiscount());
                            }
                            showData();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            noData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutletOfferResponse> call, Throwable t) {
                showError();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void selectOutletOffer() {
        loadData();
        AuthPref authPref = new AuthPref(context);
        SelectOfferRequest selectOfferRequest = new SelectOfferRequest();
        selectOfferRequest.setOutletId(selectedOffer.getOutletId());
        selectOfferRequest.setDiscountId(selectedOffer.getDiscountId());
        DiscountInterface discountInterface = RestClient.getRetrofitFabloInventoryService(context).create(DiscountInterface.class);
        Call<BasicResponse> call = discountInterface.addOfferOutlet(authPref.getBearerToken(), selectOfferRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                showData();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            SuccessAlert successAlert = SuccessAlert.getInstance();
                            getOutletOffer();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                showError();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void loadData() {
        binding.cardOffer.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.cardOffer.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieNoData.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.cardOffer.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieLoading.cancelAnimation();
    }

    private void showError() {
        binding.cardOffer.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
        binding.lottieLoading.cancelAnimation();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSelectDiscount) {
            DiscountSelectorBottomSheet discountSelectorBottomSheet = new DiscountSelectorBottomSheet();
            discountSelectorBottomSheet.show(getSupportFragmentManager(), "selector");
        } else if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectOffer selectOffer) {
        if (selectOffer != null) {
            selectedOffer = selectOffer;
            selectOutletOffer();
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
    protected void onResume() {
        super.onResume();
        getOutletOffer();
    }
}