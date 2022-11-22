package com.myfablo.seller.interfaces;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.manage.discount.models.create.AddDiscountRequest;
import com.myfablo.seller.manage.discount.models.outlet.OutletOfferResponse;
import com.myfablo.seller.manage.discount.models.running.RunningOffersResponse;
import com.myfablo.seller.manage.discount.models.running.SelectOfferRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DiscountInterface {

    @GET("discount")
    Call<RunningOffersResponse> getRunningOffers(@Header("Authorization") String token);

    @POST("discount")
    Call<BasicResponse> createDiscount(@Header("Authorization") String token, @Body AddDiscountRequest addDiscountRequest);

    @POST("outlet/discount")
    Call<BasicResponse> addOfferOutlet(@Header("Authorization") String token, @Body SelectOfferRequest selectOfferRequest);

    @GET("outlet/discount/{outletId}")
    Call<OutletOfferResponse> getOutletOffer(@Header("Authorization") String token, @Path("outletId") String outletId);

}
