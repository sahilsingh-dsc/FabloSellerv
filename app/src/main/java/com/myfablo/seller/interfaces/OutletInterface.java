package com.myfablo.seller.interfaces;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.home.outlets.models.OutletsResponse;
import com.myfablo.seller.home.outlets.models.single.OutletDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OutletInterface {

    @GET("outlet/seller")
    Call<OutletsResponse> getAllOutlets(@Header("Authorization") String token);

    @GET("outlet/seller")
    Call<OutletsResponse> getOutletsByStatus(@Header("Authorization") String token, @Query("mode") int mode);

    @GET("outlet/single/{outletId}")
    Call<OutletDetailsResponse> getSingleOutletDetails(@Header("Authorization") String token, @Path("outletId") String outletId);

    @GET("outlet/status/{outletId}")
    Call<BasicResponse> updateOutletStatus(@Header("Authorization") String token, @Path("outletId") String outletId);

}
