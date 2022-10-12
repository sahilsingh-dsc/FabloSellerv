package com.myfablo.seller.interfaces;

import com.myfablo.seller.catalogue.customization.model.ProductCustomizationResponse;
import com.myfablo.seller.catalogue.product.model.ProductsByCategoryRequest;
import com.myfablo.seller.catalogue.product.model.ProductsByCategoryResponse;
import com.myfablo.seller.catalogue.product.model.SingleProductRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductInterface {

    @POST("/open/products/getProductsForCategory")
    Call<ProductsByCategoryResponse> getProductsForCategory(@Body ProductsByCategoryRequest productsByCategoryRequest);

    @POST("/open/product/get")
    Call<ProductCustomizationResponse> getSingleProduct(@Body SingleProductRequest singleProductRequest);

}
