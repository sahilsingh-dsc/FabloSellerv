package com.myfablo.seller.catalogue.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.category.model.CategoryResponse;
import com.myfablo.seller.catalogue.category.model.Result;
import com.myfablo.seller.catalogue.category.model.SubCategoryRequest;
import com.myfablo.seller.catalogue.product.ProductRecyclerAdapter;
import com.myfablo.seller.catalogue.product.model.ProductsByCategoryRequest;
import com.myfablo.seller.catalogue.product.model.ProductsByCategoryResponse;
import com.myfablo.seller.common.CommonRequest;
import com.myfablo.seller.databinding.ActivityCategoryDetailsBinding;
import com.myfablo.seller.interfaces.CatalogueInterface;
import com.myfablo.seller.interfaces.ProductInterface;
import com.myfablo.seller.orders.model.MpOrderStatusUpdateRequest;
import com.myfablo.seller.preference.SellerPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailsActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ActivityCategoryDetailsBinding binding;
    private Context context;
    private Result result;

    private static final String TAG = "CategoryDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = CategoryDetailsActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        result = getIntent().getParcelableExtra("category");
        binding.recyclerProducts.setLayoutManager(new GridLayoutManager(context, 2));
        binding.recyclerSubCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerProducts.hasFixedSize();
        binding.swipeRefresh.setOnRefreshListener(this);
        if (result != null) {
            binding.tvCategoryName.setText(result.getName());
            binding.tvCategoryDescription.setText(result.getDescription());
            Glide.with(context).load(result.getImageUrl()).placeholder(R.drawable.ic_placeholder).into(binding.ivCategoryImage);

            if (result.getHasChildren() == Constant.STATUS_TRUE) {
                fetchSubCategories();
            } else {
                fetchProductByCategory();
            }

        }
    }

    private void fetchSubCategories() {
        SellerPref sellerPref = new SellerPref(context);
        SubCategoryRequest subCategoryRequest = new SubCategoryRequest();
        subCategoryRequest.setApiKey(Constant.YELO_API_KEY);
        subCategoryRequest.setMarketplaceUserId(Constant.MP_USER_ID);
        subCategoryRequest.setUserId(sellerPref.getUserId());
        subCategoryRequest.setParentCategoryId(result.getCatalogueId());

        CatalogueInterface catalogueInterface = RestClient.getRetrofitYeloInstance(context).create(CatalogueInterface.class);
        Call<CategoryResponse> responseCall = catalogueInterface.getSubCategories(subCategoryRequest);
        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        SubCategoryRecyclerAdapter subCategoryRecyclerAdapter = new SubCategoryRecyclerAdapter(context, response.body().getData().getResult());
                        binding.recyclerSubCategory.setAdapter(subCategoryRecyclerAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void fetchProductByCategory() {
        loadData();
        SellerPref sellerPref = new SellerPref(context);

        ProductsByCategoryRequest productsByCategoryRequest = new ProductsByCategoryRequest();
        productsByCategoryRequest.setApiKey(Constant.YELO_API_KEY);
        productsByCategoryRequest.setMarketplaceUserId(Constant.MP_USER_ID);
        productsByCategoryRequest.setUserId(sellerPref.getUserId());
        productsByCategoryRequest.setOffset(Constant.REQUEST_SET_OFF_FALSE);
        productsByCategoryRequest.setLimit(50);
        productsByCategoryRequest.setParentCategoryId(result.getCatalogueId());

        ProductInterface productInterface = RestClient.getRetrofitYeloInstance(context).create(ProductInterface.class);
        Call<ProductsByCategoryResponse> call = productInterface.getProductsForCategory(productsByCategoryRequest);
        call.enqueue(new Callback<ProductsByCategoryResponse>() {
            @Override
            public void onResponse(Call<ProductsByCategoryResponse> call, Response<ProductsByCategoryResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getData().size() == 0) {
                            noData();
                        } else {
                            ProductRecyclerAdapter productRecyclerAdapter = new ProductRecyclerAdapter(context, response.body().getData());
                            binding.recyclerProducts.setAdapter(productRecyclerAdapter);
                            showData();
                            if (binding.swipeRefresh.isRefreshing()) {
                                binding.swipeRefresh.setRefreshing(false);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsByCategoryResponse> call, Throwable t) {
                noData();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void fetchProductBySubCategory(Result subCategoryResult) {
        loadData();
        SellerPref sellerPref = new SellerPref(context);

        ProductsByCategoryRequest productsByCategoryRequest = new ProductsByCategoryRequest();
        productsByCategoryRequest.setApiKey(Constant.YELO_API_KEY);
        productsByCategoryRequest.setMarketplaceUserId(Constant.MP_USER_ID);
        productsByCategoryRequest.setUserId(sellerPref.getUserId());
        productsByCategoryRequest.setOffset(Constant.REQUEST_SET_OFF_FALSE);
        productsByCategoryRequest.setLimit(50);
        productsByCategoryRequest.setParentCategoryId(subCategoryResult.getCatalogueId());

        ProductInterface productInterface = RestClient.getRetrofitYeloInstance(context).create(ProductInterface.class);
        Call<ProductsByCategoryResponse> call = productInterface.getProductsForCategory(productsByCategoryRequest);
        call.enqueue(new Callback<ProductsByCategoryResponse>() {
            @Override
            public void onResponse(Call<ProductsByCategoryResponse> call, Response<ProductsByCategoryResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getData().size() == 0) {
                            noData();
                        } else {
                            ProductRecyclerAdapter productRecyclerAdapter = new ProductRecyclerAdapter(context, response.body().getData());
                            binding.recyclerProducts.setAdapter(productRecyclerAdapter);
                            showData();
                            if (binding.swipeRefresh.isRefreshing()) {
                                binding.swipeRefresh.setRefreshing(false);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsByCategoryResponse> call, Throwable t) {
                noData();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void loadData() {
        binding.recyclerProducts.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.VISIBLE);
    }

    private void showData() {
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerProducts.setVisibility(View.VISIBLE);
    }

    private void noData() {
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerProducts.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Result subCategoryResult) {
        if (subCategoryResult != null) {
            fetchProductBySubCategory(subCategoryResult);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        fetchProductByCategory();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}