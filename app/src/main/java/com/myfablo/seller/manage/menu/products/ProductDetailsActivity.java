package com.myfablo.seller.manage.menu.products;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityProductDetailsBinding;
import com.myfablo.seller.manage.menu.addons.AddonsCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.addons.OutletAddOnsContract;
import com.myfablo.seller.manage.menu.addons.OutletAddonsInterface;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;
import com.myfablo.seller.manage.menu.models.StockUpdate;
import com.myfablo.seller.manage.menu.variations.ProductVariationResponse;
import com.myfablo.seller.manage.menu.variations.Variant;
import com.myfablo.seller.manage.menu.variations.VariantRecyclerAdapter;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.ResponseFormatter;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;
import com.myfablo.seller.utils.alerts.ProductStockAlert;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.retrofit.RestClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private String variationName;
    private Integer minVariation;
    private Integer maxVariation;

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
        initScroll();
        initContract();
        showViewData();
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
        binding.lvAddons.setOnClickListener(this);
        binding.lvVariations.setOnClickListener(this);
        binding.viewStock.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerItems.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initScroll() {
        binding.scrollProductDetails.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= 100) {
                    binding.cardProductDetails.setCardElevation(10);
                    binding.tvHeaderProductName.setVisibility(View.VISIBLE);
                } else {
                    binding.cardProductDetails.setCardElevation(0);
                    binding.tvHeaderProductName.setVisibility(View.INVISIBLE);
                }
            }
        });
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
        selectVariationsTab();
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
        binding.tvHeaderProductName.setText(productDetails.getProductName());
        showStockDetails();
    }

    private void showProductPrice() {
        if (productDetails.getHasCustomization()) {
            binding.tvProductPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(productDetails.getDisplayPrice()));
        } else {
            binding.tvProductPrice.setText(new ResponseFormatter(context).getPriceWithSymbol(productDetails.getProductPrice()));
        }
    }

    private void showStockDetails() {
        if (productDetails.getInStock()) {
            binding.tvStockStatus.setText("In Stock");
            binding.tvStockStatus.setTextColor(context.getResources().getColor(R.color.color_text_title));
            binding.switchStock.setChecked(true);
        } else {
            binding.tvStockStatus.setText("Out of Stock");
            binding.tvStockStatus.setTextColor(context.getResources().getColor(R.color.color_error));
            binding.switchStock.setChecked(false);
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

    private void updateProductStock(StockUpdate stockUpdate) {
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<BasicResponse> call = menuInterface.changeProductStock(stockUpdate.getProductId());
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            getProductDetails();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            OhSnapErrorAlert ohSnapErrorAlert = OhSnapErrorAlert.getInstance();
                            ohSnapErrorAlert.showAlert(context, "Stock update can't be performed, Please contact your dedicated manager.");
                        }
                    }
                } else if (response.code() == Constant.HTTP_CLIENT_ERROR) {
                    showError();
                } else if (response.code() == Constant.HTTP_SERVER_ERROR) {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                showError();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getProductDetails() {
        loadProductDetails();
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        menuInterface.getProductDetails(productId)
                .enqueue(new Callback<ProductDetailsResponse>() {
                    @Override
                    public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubcode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    showProductDetailsData();
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
                                    variationName = response.body().getItems().getVariationName();
                                    minVariation = response.body().getItems().getMinSelection();
                                    maxVariation = response.body().getItems().getMaxSelection();
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

    private void loadProductDetails() {
        binding.scrollProductDetails.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieLoading.playAnimation();
    }

    private void showProductDetailsData() {
        binding.scrollProductDetails.setVisibility(View.VISIBLE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieLoading.cancelAnimation();
    }

    private void loadData() {
        binding.recyclerItems.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        if (type.equals("variations")) {
            binding.lhVariation.setVisibility(View.GONE);
            binding.tvNoData.setText("There are no variations in this product");
        } else if (type.equals("addons")) {
            binding.lhVariation.setVisibility(View.GONE);
            binding.tvNoData.setText("There are no addons in this product");
        }
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerItems.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
    }

    private void showData() {
        if (type.equals("variations")) {
            binding.tvVariationName.setText(variationName);
            binding.tvSelection.setText(new ResponseFormatter(context).getMinMaxSelection(minVariation, maxVariation));
            binding.lhVariation.setVisibility(View.VISIBLE);
            binding.viewVariationsLine.setVisibility(View.VISIBLE);
        } else if (type.equals("addons")) {
            binding.lhVariation.setVisibility(View.GONE);
            binding.viewVariationsLine.setVisibility(View.GONE);
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StockUpdate stockUpdate) {
        if (stockUpdate != null) {
            updateProductStock(stockUpdate);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.lvVariations) {
            selectVariationsTab();
        } else if (view == binding.lvAddons) {
            selectAddOnsTab();
        } else if (view == binding.viewStock) {
            ProductStockAlert productStockAlert = ProductStockAlert.getInstance();
            productStockAlert.showAlert(context, productDetails.getInStock(), productDetails.getProductId(), productDetails.getProductName());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}