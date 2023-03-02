package com.myfablo.seller.utils.retrofit;

import android.content.Context;

import com.myfablo.seller.utils.AppConfig;
import com.myfablo.seller.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestClient {

    private static Retrofit retrofitFabloUserService;
    private static Retrofit retrofitFabloInventoryService;
    private static Retrofit retrofitFabloOrderService;
    private static Retrofit retrofitFabloAdminService;

    public static Retrofit getRetrofitFabloUserService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloUserService == null) {
            retrofitFabloUserService = new Retrofit.Builder()
                    .baseUrl(new AppConfig().getBaseUrl(Constant.APP_ENV_DEVELOPMENT).getUserBaseUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloUserService;
    }

    public static Retrofit getRetrofitFabloInventoryService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();

        if (retrofitFabloInventoryService == null) {
            retrofitFabloInventoryService = new Retrofit.Builder()
                    .baseUrl(new AppConfig().getBaseUrl(Constant.APP_ENV_DEVELOPMENT).getInventoryBaseUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloInventoryService;
    }

    public static Retrofit getRetrofitFabloOrderService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloOrderService == null) {
            retrofitFabloOrderService = new Retrofit.Builder()
                    .baseUrl(new AppConfig().getBaseUrl(Constant.APP_ENV_DEVELOPMENT).getOrderBaseUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloOrderService;
    }

    public static Retrofit getRetrofitFabloAdminService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloAdminService == null) {
            retrofitFabloAdminService = new Retrofit.Builder()
                    .baseUrl(new AppConfig().getBaseUrl(Constant.APP_ENV_DEVELOPMENT).getAdminBaseUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloAdminService;
    }

}
