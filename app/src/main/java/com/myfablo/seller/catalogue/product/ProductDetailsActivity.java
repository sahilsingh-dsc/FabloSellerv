package com.myfablo.seller.catalogue.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.catalogue.customization.model.ProductCustomizationResponse;
import com.myfablo.seller.catalogue.product.model.Datum;
import com.myfablo.seller.catalogue.product.model.ProductsByCategoryResponse;
import com.myfablo.seller.catalogue.product.model.SingleProductRequest;
import com.myfablo.seller.databinding.ActivityProductDetailsBinding;
import com.myfablo.seller.interfaces.ProductInterface;
import com.myfablo.seller.preference.SellerPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductDetailsBinding binding;
    private Context context;
    private Datum productDatum;
    private int productCustomStatus;

    private static final String TAG = "ProductDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = ProductDetailsActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.recyclerCustomization.setLayoutManager(new LinearLayoutManager(context));
        productDatum = getIntent().getParcelableExtra("product");
        productCustomStatus = getIntent().getIntExtra("custom", Constant.STATUS_FALSE);
        showProductDetails();
        fetchCustomization();
    }

    private void showProductDetails() {
        Glide.with(context).load(productDatum.getImageUrl()).placeholder(R.drawable.ic_placeholder).into(binding.ivProductImage);
        binding.tvProductName.setText(productDatum.getName());
        binding.tvProductPrice.setText("₹" + productDatum.getPrice());

        if (productDatum.getIsEnabled() == Constant.STATUS_TRUE) {
            binding.tvProductStatus.setText(R.string.str_status_active);
            binding.tvProductStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_active, null));
        } else {
            binding.tvProductStatus.setText(R.string.str_status_inactive);
            binding.tvProductStatus.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_status_inactive, null));
        }

        if (productCustomStatus == Constant.STATUS_TRUE) {
            binding.tvCustomizationStatus.setVisibility(View.VISIBLE);
            binding.lvCustomization.setVisibility(View.VISIBLE);
        } else {
            binding.tvCustomizationStatus.setVisibility(View.GONE);
            binding.lvCustomization.setVisibility(View.GONE);
        }

    }

    private void fetchCustomization() {
        SellerPref sellerPref = new SellerPref(context);
        SingleProductRequest singleProductRequest = new SingleProductRequest();
        singleProductRequest.setApiKey(Constant.YELO_API_KEY);
        singleProductRequest.setMarketplaceUserId(Constant.MP_USER_ID);
        singleProductRequest.setUserId(sellerPref.getUserId());
        singleProductRequest.setOffset(Constant.REQUEST_SET_OFF_FALSE);
        singleProductRequest.setAppType(Constant.MP_USER_ACCESS_TYPE_MERCHANT);
        singleProductRequest.setLimit(25);
        singleProductRequest.setProductId(productDatum.getProductId());
        ProductInterface productInterface = RestClient.getRetrofitYeloInstance(context).create(ProductInterface.class);
        Call<ProductCustomizationResponse> call = productInterface.getSingleProduct(singleProductRequest);
        call.enqueue(new Callback<ProductCustomizationResponse>() {
            @Override
            public void onResponse(Call<ProductCustomizationResponse> call, Response<ProductCustomizationResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getData().getCustomization() != null) {
                            CustomizationRecyclerAdapter customizationRecyclerAdapter = new CustomizationRecyclerAdapter(context, response.body().getData().getCustomization());
                            binding.recyclerCustomization.setAdapter(customizationRecyclerAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductCustomizationResponse> call, Throwable t) {
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
}