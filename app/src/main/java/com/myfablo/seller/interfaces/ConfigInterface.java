package com.myfablo.seller.interfaces;

import com.myfablo.seller.common.AppVersionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConfigInterface {

    @GET("config/version")
    Call<AppVersionResponse> getAppVersion();

}
