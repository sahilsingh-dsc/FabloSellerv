package com.myfablo.seller.utils.interfaces;

import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.manage.menu.add.models.AddCategoryRequest;
import com.myfablo.seller.manage.menu.add.subcategory.AddSubCategoryRequest;
import com.myfablo.seller.manage.menu.addons.models.addons_get.OutletAddonsResponse;
import com.myfablo.seller.manage.menu.models.CategoryResponse;
import com.myfablo.seller.manage.menu.models.FoodMenuResponse;
import com.myfablo.seller.manage.menu.models.customization.CustomizationResponse;
import com.myfablo.seller.manage.menu.products.ProductDetailsResponse;
import com.myfablo.seller.manage.menu.variations.ProductVariationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenuInterface {

    @GET("menu/{outletId}")
    Call<FoodMenuResponse> getFullMenu(@Path("outletId") String outletId);

    @GET("menu/category/{outletId}")
    Call<CategoryResponse> getCategory(@Path("outletId") String outletId);

    @GET("menu/customization/{productId}")
    Call<CustomizationResponse> getCustomization(@Path("productId") String product_id);

    @POST("menu/category")
    Call<BasicResponse> addCategory(@Body AddCategoryRequest addCategoryRequest);

    @POST("menu/category")
    Call<BasicResponse> addSubCategory(@Body AddSubCategoryRequest addSubCategoryRequest);

    @GET("menu/stock/{productId}")
    Call<BasicResponse> changeProductStock(@Path("productId") String productId);

    @GET("menu/addOn/{outletId}")
    Call<OutletAddonsResponse> getOutletAddons(@Path("outletId") String outletId);

    @GET("menu/product/{productId}")
    Call<ProductDetailsResponse> getProductDetails(@Path("productId") String productId);

    @GET("menu/product/addOn/{productId}")
    Call<OutletAddonsResponse> getProductAddons(@Path("productId") String productId);

    @GET("menu/customization/{productId}")
    Call<ProductVariationResponse> getProductVariation(@Path("productId") String productId);

}
