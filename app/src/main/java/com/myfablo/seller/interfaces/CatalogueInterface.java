package com.myfablo.seller.interfaces;

import com.myfablo.seller.catalogue.category.model.AddCategoryRequest;
import com.myfablo.seller.catalogue.category.model.CategoryResponse;
import com.myfablo.seller.catalogue.category.model.SubCategoryRequest;
import com.myfablo.seller.common.CommonRequest;
import com.myfablo.seller.common.CommonResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CatalogueInterface {

    @POST("open/catalogue/getAll")
    Call<CategoryResponse> getAllCategories(@Body CommonRequest commonRequest);

    @POST("open/catalogue/getAll")
    Call<CategoryResponse> getSubCategories(@Body SubCategoryRequest subCategoryRequest);

    @POST("open/catalogue/add")
    Call<CommonResponse> addCategory(@Body AddCategoryRequest addCategoryRequest);

}
