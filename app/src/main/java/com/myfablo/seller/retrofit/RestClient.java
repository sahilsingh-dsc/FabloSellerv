package com.myfablo.seller.retrofit;

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

    private static final String YELO_PROD_BASE_URL = "https://api.yelo.red/";
    private static final String MP_PROD_BASE_URL = "https://dashboard.myfablo.com/api/";
    private static final String FABLO_USER_SERVICE_BASE_URL = "https://user.fablocdn.com/v1/";
    private static final String FABLO_INVENTORY_SERVICE_BASE_URL = "https://inventory.fablocdn.com/v1/";
    private static final String FABLO_ORDER_SERVICE_BASE_URL = "https://order.fablocdn.com/v1/";
    private static final String FABLO_ADMIN_SERVICE_BASE_URL = "https://admin.fablocdn.com/v1/";

    public static Retrofit getRetrofitYeloInstance(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();

        if (retrofitYelo == null) {
            retrofitYelo = new Retrofit.Builder()
                    .baseUrl(YELO_PROD_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitYelo;
    }

    public static Retrofit getRetrofitMpInstance(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();

        if (retrofitMp == null) {
            retrofitMp = new Retrofit.Builder()
                    .baseUrl(MP_PROD_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitMp;
    }

    public static Retrofit getRetrofitFabloUserService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloUserService == null) {
            retrofitFabloUserService = new Retrofit.Builder()
                    .baseUrl(FABLO_USER_SERVICE_BASE_URL)
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
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        if (retrofitFabloInventoryService == null) {
            retrofitFabloInventoryService = new Retrofit.Builder()
                    .baseUrl(FABLO_INVENTORY_SERVICE_BASE_URL)
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
                    .baseUrl(FABLO_ORDER_SERVICE_BASE_URL)
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
                    .baseUrl(FABLO_ADMIN_SERVICE_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofitFabloAdminService;
    }

}
