package com.myfablo.seller.catalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.catalogue.category.CategoryRecyclerAdapter;
import com.myfablo.seller.catalogue.category.model.CategoryResponse;
import com.myfablo.seller.common.CommonRequest;
import com.myfablo.seller.databinding.ActivityCatalogueBinding;
import com.myfablo.seller.interfaces.CatalogueInterface;
import com.myfablo.seller.preference.SellerPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogueActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ActivityCatalogueBinding binding;
    private Context context;

    private static final String TAG = "CatalogueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatalogueBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = CatalogueActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.recyclerCategory.setLayoutManager(new GridLayoutManager(context, 2));
        binding.recyclerCategory.hasFixedSize();
        binding.swipeRefresh.setOnRefreshListener(this);
        fetchCategories();
    }

    private void fetchCategories() {
        loadData();
        SellerPref sellerPref = new SellerPref(context);
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setApiKey(Constant.YELO_API_KEY);
        commonRequest.setMarketplaceUserId(Constant.MP_USER_ID);
        commonRequest.setUserId(sellerPref.getUserId());

        CatalogueInterface catalogueInterface = RestClient.getRetrofitYeloInstance(context).create(CatalogueInterface.class);
        Call<CategoryResponse> responseCall = catalogueInterface.getAllCategories(commonRequest);
        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        CategoryRecyclerAdapter categoryRecyclerAdapter = new CategoryRecyclerAdapter(context, response.body().getData().getResult());
                        binding.recyclerCategory.setAdapter(categoryRecyclerAdapter);
                        showData();
                        if (binding.swipeRefresh.isRefreshing()) {
                            binding.swipeRefresh.setRefreshing(false);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                noData();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }

    private void loadData() {
        binding.recyclerCategory.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.VISIBLE);
    }

    private void showData() {
        binding.lvNoData.setVisibility(View.GONE);
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerCategory.setVisibility(View.VISIBLE);
    }

    private void noData() {
        binding.lvLoadData.setVisibility(View.GONE);
        binding.recyclerCategory.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        fetchCategories();
    }
}