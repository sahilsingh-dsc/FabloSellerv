package com.myfablo.seller.interfaces;

import com.myfablo.seller.auth.models.UserLoginRequest;
import com.myfablo.seller.auth.models.UserLoginResponse;
import com.myfablo.seller.auth.models.VerifyOtpRequest;
import com.myfablo.seller.auth.models.VerifyOtpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthInterface {

    @POST("auth/login")
    Call<UserLoginResponse> userLogin(@Body UserLoginRequest userLoginRequest);

    @POST("auth/verify")
    Call<VerifyOtpResponse> verifyOtp(@Body VerifyOtpRequest verifyOtpRequest);

}
