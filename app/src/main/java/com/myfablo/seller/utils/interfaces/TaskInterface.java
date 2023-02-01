package com.myfablo.seller.utils.interfaces;

import com.myfablo.seller.orders.model.AcceptRejectOrderRequest;
import com.myfablo.seller.orders.model.AcceptRejectOrderResponse;
import com.myfablo.seller.orders.model.UpdateOrderStatusRequest;
import com.myfablo.seller.orders.model.UpdateOrderStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskInterface {

    @POST("open/task/acceptReject")
    Call<AcceptRejectOrderResponse> acceptRejectOrder(@Body AcceptRejectOrderRequest acceptRejectOrderRequest);

    @POST("open/task/updateStatus")
    Call<UpdateOrderStatusResponse> updateOrderStatus(@Body UpdateOrderStatusRequest updateOrderStatusRequest);

}
