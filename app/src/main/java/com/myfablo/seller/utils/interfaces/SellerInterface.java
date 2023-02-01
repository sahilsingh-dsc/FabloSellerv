package com.myfablo.seller.utils.interfaces;

import com.myfablo.seller.auth.models.details.SellerDetailsResponse;
import com.myfablo.seller.manage.support.models.manager.SupportManagerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SellerInterface {

    @GET("seller/info")
    Call<SellerDetailsResponse> getSellerDetails(@Header("Authorization") String token);

    @GET("seller/agent/info")
    Call<SupportManagerResponse> getSupportManager(@Header("Authorization") String token);

}
