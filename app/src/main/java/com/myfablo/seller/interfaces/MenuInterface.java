package com.myfablo.seller.interfaces;

import com.myfablo.seller.manage.menu.models.CategoryResponse;
import com.myfablo.seller.manage.menu.models.FoodMenuResponse;
import com.myfablo.seller.manage.menu.models.customization.CustomizationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuInterface {

    @GET("menu/{outletId}")
    Call<FoodMenuResponse> getFullMenu(@Path("outletId") String outletId);

    @GET("menu/category/{outletId}")
    Call<CategoryResponse> getCategory(@Path("outletId") String outletId);

    @GET("menu/customization/{productId}")
    Call<CustomizationResponse> getCustomization(@Path("productId") String product_id);

}
