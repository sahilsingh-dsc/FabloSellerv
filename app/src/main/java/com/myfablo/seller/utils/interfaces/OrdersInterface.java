package com.myfablo.seller.utils.interfaces;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.home.account.insights.models.SellerProfitResponse;
import com.myfablo.seller.manage.orders.model.OrderResponse;
import com.myfablo.seller.manage.orders.model.OrderStatusChangeRequest;
import com.myfablo.seller.orders.model.AllOrderRequest;
import com.myfablo.seller.orders.model.AllOrderResponse;
import com.myfablo.seller.orders.model.order_get.OrderDetailsResponse;
import com.myfablo.seller.orders.v2.SellerOrdersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrdersInterface {

    @POST("open/orders/getAll")
    Call<AllOrderResponse> getAllOrders(@Body AllOrderRequest allOrderRequest);

    @GET("order/upcoming/{outletId}")
    Call<SellerOrdersResponse> getOrders(@Path("outletId") String outletId, @Query("status") String status);

    @POST("order/status")
    Call<BasicResponse> changeOrderStatus(@Header("Authorization") String token, @Body OrderStatusChangeRequest orderStatusChangeRequest);

    @GET("order/details/{orderId}")
    Call<OrderDetailsResponse> getOrderDetails(@Path("orderId") String orderId);

    @GET("order/outlet/{outletId}")
    Call<SellerOrdersResponse> getOrderByDate(@Path("outletId") String outletId, @Query("status") String status, @Query("from") String from, @Query("to") String to);

    @GET("order/seller")
    Call<SellerOrdersResponse> getOrderBySeller(@Header("Authorization") String token);

    @GET("order/seller/profit")
    Call<SellerProfitResponse> getSellerProfit(@Header("Authorization") String token, @Query("from") String from, @Query("to") String to);

    @GET("order/outlet/{outletId}/profit")
    Call<SellerProfitResponse> getOutletProfit(@Header("Authorization") String token, @Path("outletId") String outletId, @Query("from") String from, @Query("to") String to);

}