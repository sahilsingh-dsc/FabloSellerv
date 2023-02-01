package com.myfablo.seller.utils.retrofit;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestClient {

    private static Retrofit retrofitYelo;
    private static Retrofit retrofitMp;
    private static Retrofit retrofitFabloUserService;
    private static Retrofit retrofitFabloInventoryService;
    private static Retrofit retrofitFabloOrderService;
    private static Retrofit retrofitFabloAdminService;

    private static final String PROD_FABLO_USER_SERVICE_BASE_URL = "https://user.fablocdn.com/v1/";
    private static final String PROD_FABLO_INVENTORY_SERVICE_BASE_URL = "https://inventory.fablocdn.com/v1/";
    private static final String PROD_FABLO_ORDER_SERVICE_BASE_URL = "https://order.fablocdn.com/v1/";
    private static final String PROD_FABLO_ADMIN_SERVICE_BASE_URL = "https://admin.fablocdn.com/v1/";

    private static final String STAGE_FABLO_USER_SERVICE_BASE_URL = "https://user.fablocdn.com/v1/";
    private static final String STAGE_FABLO_INVENTORY_SERVICE_BASE_URL = "https://inventory.fablocdn.com/v1/";
    private static final String STAGE_FABLO_ORDER_SERVICE_BASE_URL = "https://order.fablocdn.com/v1/";
    private static final String STAGE_FABLO_ADMIN_SERVICE_BASE_URL = "https://admin.fablocdn.com/v1/";

    private static final String DEV_FABLO_USER_SERVICE_BASE_URL = "http://139.59.60.119:4006/v1/";
    private static final String DEV_FABLO_INVENTORY_SERVICE_BASE_URL = "http://139.59.60.119:9000/v1/";
    private static final String DEV_FABLO_ORDER_SERVICE_BASE_URL = "http://139.59.60.119:4007/v1/";
    private static final String DEV_FABLO_ADMIN_SERVICE_BASE_URL = "http://139.59.60.119:4777/v1/";


    public static Retrofit getRetrofitFabloUserService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloUserService == null) {
            retrofitFabloUserService = new Retrofit.Builder()
                    .baseUrl(DEV_FABLO_USER_SERVICE_BASE_URL)
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
                    .baseUrl(DEV_FABLO_INVENTORY_SERVICE_BASE_URL)
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
                    .baseUrl(DEV_FABLO_ORDER_SERVICE_BASE_URL)
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
                    .baseUrl(DEV_FABLO_ADMIN_SERVICE_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloAdminService;
    }

}
