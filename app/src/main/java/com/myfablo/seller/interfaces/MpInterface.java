package com.myfablo.seller.interfaces;

import com.myfablo.seller.orders.model.MpOrderStatusUpdateRequest;
import com.myfablo.seller.orders.model.UpdateOrderStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MpInterface {

    @POST("update_job_status")
    Call<UpdateOrderStatusResponse> updateOrderStatus(@Body MpOrderStatusUpdateRequest mpOrderStatusUpdateRequest);


}
