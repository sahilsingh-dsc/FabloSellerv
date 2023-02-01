package com.myfablo.seller.manage.menu.products;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityProductDetailsBinding;
import com.myfablo.seller.manage.menu.addons.AddonsCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.addons.OutletAddOnsContract;
import com.myfablo.seller.manage.menu.addons.OutletAddonsInterface;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;
import com.myfablo.seller.manage.menu.variations.ProductVariationResponse;
import com.myfablo.seller.manage.menu.variations.Variant;
import com.myfablo.seller.manage.menu.variations.VariantRecyclerAdapter;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.ResponseFormatter;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.retrofit.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProductDetailsActivity";
    private ActivityProductDetailsBinding binding;
    private Context context;
    private String productId;
    private Items productDetails;
    private OutletAddOnsContract outletAddOnsContract;
    private String type;

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
        initClick();
        initRecycler();
        initContract();
        showViewData();
        selectVariationsTab();
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
        binding.lvAddons.setOnClickListener(this);
        binding.lvVariations.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerItems.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initContract() {
        outletAddOnsContract = new OutletAddOnsContract(context, new OutletAddonsInterface() {
            @Override
            public void onContractProgress() {
                loadData();
            }

            @Override
            public void onContractResponse(List<Item> items) {
                showData();
                showAddOnsList(items);
            }

            @Override
            public void onContractNotFound() {
                noData();
            }

            @Override
            public void onContractFailure() {
                showError();
            }
        });
    }

    private void selectVariationsTab() {
        type = "variations";
        binding.viewVariations.setVisibility(View.VISIBLE);
        binding.viewAddons.setVisibility(View.INVISIBLE);
        binding.tvVariations.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvAddons.setTextColor(getResources().getColor(R.color.color_text_description));
        getProductVariations();
    }

    private void selectAddOnsTab() {
        type = "addons";
        binding.viewAddons.setVisibility(View.VISIBLE);
        binding.viewVariations.setVisibility(View.INVISIBLE);
        binding.tvAddons.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvVariations.setTextColor(getResources().getColor(R.color.color_text_description));
        outletAddOnsContract.getProductAddOns(productId);
    }

    private void showViewData() {
        productId = getIntent().getStringExtra("productId");
        getProductDetails();
    }

    private void showAddOnsList(List<Item> items) {
        AddonsCategoryRecyclerAdapter addonsCategoryRecyclerAdapter = new AddonsCategoryRecyclerAdapter(context, items);
        binding.recyclerItems.setAdapter(addonsCategoryRecyclerAdapter);
    }

    private void showProductDetails() {
        showProductImage();
        showProductPrice();
        showProductServing();
        showProductDesc();
        binding.tvProductName.setText(productDetails.getProductName());
    }

    private void showProductPrice() {
        if (productDetails.getHasCustomization()) {
            binding.tvProductPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(productDetails.getDisplayPrice()));
        } else {
            binding.tvProductPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(productDetails.getProductPrice()));
        }
    }

    private void showProductDesc() {
        if (productDetails.getProductDesc().isEmpty()) {
            binding.tvProductDescription.setVisibility(View.GONE);
        } else {
            binding.tvProductDescription.setVisibility(View.VISIBLE);
            binding.tvProductDescription.setText(productDetails.getProductDesc());
        }
    }

    private void showProductImage() {
        if (productDetails.getProductImage().isEmpty()) {
            binding.cardImage.setVisibility(View.GONE);
        } else {
            binding.cardImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(productDetails.getProductImage()).into(binding.ivProductImage);
        }
    }

    private void showProductServing() {
        if (productDetails.getIsVeg()) {
            binding.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_veg));
        } else {
            binding.ivServingType.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_food_type_non_veg));
        }
    }

    private void getProductDetails() {
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        menuInterface.getProductDetails(productId)
                .enqueue(new Callback<ProductDetailsResponse>() {
                    @Override
                    public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubcode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    productDetails = response.body().getItems();
                                    showProductDetails();
                                } else if (response.body().getSubcode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {

                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t);
                    }
                });
    }

    private void getProductVariations() {
        loadData();
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        menuInterface.getProductVariation(productId)
                .enqueue(new Callback<ProductVariationResponse>() {
                    @Override
                    public void onResponse(Call<ProductVariationResponse> call, Response<ProductVariationResponse> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    showVariantsList(response.body().getItems().getVariantList());
                                    showData();
                                } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                                    noData();
                                }
                            }
                        } else {
                            showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductVariationResponse> call, Throwable t) {
                        showError();
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private void showVariantsList(List<Variant> variantList) {
        VariantRecyclerAdapter variantRecyclerAdapter = new VariantRecyclerAdapter(context, variantList);
        binding.recyclerItems.setAdapter(variantRecyclerAdapter);
    }

    private void loadData() {
        if (type.equals("items")) {
            binding.tvVariationName.setVisibility(View.VISIBLE);
        } else if (type.equals("addons")) {
            binding.tvVariationName.setVisibility(View.GONE);
        }
        binding.recyclerItems.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        if (type.equals("items")) {
            binding.tvVariationName.setVisibility(View.GONE);
            binding.tvNoData.setText("There are no variations in this product");
        } else if (type.equals("addons")) {
            binding.tvNoData.setText("There are no addons in this product");
        }
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerItems.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerItems.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerItems.setVisibility(View.GONE);
        binding.lottieNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.lvVariations) {
            selectVariationsTab();
        } else if (view == binding.lvAddons) {
            selectAddOnsTab();
        }
    }
}