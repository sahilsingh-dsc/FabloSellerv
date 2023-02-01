package com.myfablo.seller.manage.menu.addons;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.myfablo.seller.manage.menu.addons.models.addons_get.OutletAddonsResponse;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutletAddOnsContract {

    private static final String TAG = "OutletAddOnsContract";
    private final Context context;
    private final MenuInterface menuInterface;
    private final OutletAddonsInterface outletAddonsInterface;

    public OutletAddOnsContract(Context context, OutletAddonsInterface outletAddonsInterface) {
        this.context = context;
        this.outletAddonsInterface = outletAddonsInterface;
        menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
    }

    public void getOutletAddOns() {
        outletAddonsInterface.onContractProgress();
        OutletPref outletPref = new OutletPref(context);
        menuInterface.getOutletAddons(outletPref.getOutletId())
                .enqueue(new Callback<OutletAddonsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<OutletAddonsResponse> call, @NonNull Response<OutletAddonsResponse> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    outletAddonsInterface.onContractResponse(response.body().getItems());
                                } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                                    outletAddonsInterface.onContractNotFound();
                                }
                            }
                        } else if (response.code() == Constant.HTTP_SERVER_ERROR) {
                            outletAddonsInterface.onContractFailure();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OutletAddonsResponse> call, @NonNull Throwable t) {
                        outletAddonsInterface.onContractFailure();
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    public void getProductAddOns(String productId) {
        outletAddonsInterface.onContractProgress();
        menuInterface.getProductAddons(productId)
                .enqueue(new Callback<OutletAddonsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<OutletAddonsResponse> call, @NonNull Response<OutletAddonsResponse> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    outletAddonsInterface.onContractResponse(response.body().getItems());
                                } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                                    outletAddonsInterface.onContractNotFound();
                                }
                            }
                        } else if (response.code() == Constant.HTTP_SERVER_ERROR) {
                            outletAddonsInterface.onContractFailure();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OutletAddonsResponse> call, @NonNull Throwable t) {
                        outletAddonsInterface.onContractFailure();
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

}
