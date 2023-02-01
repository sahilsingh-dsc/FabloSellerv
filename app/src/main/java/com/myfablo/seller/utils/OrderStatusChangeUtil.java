package com.myfablo.seller.utils;

import android.content.Context;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.utils.interfaces.OrdersInterface;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.alerts.FabLoading;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusChangeUtil {

    private Context context;
    private FabLoading fabLoading;

    public OrderStatusChangeUtil(Context context) {
        this.context = context;
    }

    public void changeOrderStatus(OrderStatusChangeRequest orderStatusChangeRequest) {
        fabLoading = FabLoading.getInstance();
        AuthPref authPref = new AuthPref(context);
        fabLoading.showProgress(context);
        OrdersInterface ordersInterface = RestClient.getRetrofitFabloOrderService(context).create(OrdersInterface.class);
        Call<BasicResponse> call = ordersInterface.changeOrderStatus("Bearer "+authPref.getAuthToken(), orderStatusChangeRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            EventBus.getDefault().post("rejectOrder");
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                fabLoading.hideProgress();
            }
        });
    }

}
