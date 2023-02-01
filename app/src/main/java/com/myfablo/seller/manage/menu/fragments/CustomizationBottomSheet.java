package com.myfablo.seller.manage.menu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.databinding.BottomSheetCustomizationBinding;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.manage.menu.adapters.FoodCustomizationRecyclerAdapter;
import com.myfablo.seller.manage.menu.models.customization.CustomizationResponse;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomizationBottomSheet extends BottomSheetDialogFragment {

    private Context context;
    private BottomSheetCustomizationBinding binding;
    private String name;
    private float price;
    private String productId;

    private static final String TAG = "CustomizationBottomShee";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetCustomizationBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            intiView();
        }
        return view;
    }

    private void intiView() {
        binding.recyclerCustomization.setLayoutManager(new LinearLayoutManager(context));
        if (getArguments() != null) {
            productId = getArguments().getString("product_id");
            name = getArguments().getString("name");
            price = getArguments().getFloat("price");
            getCustomization(productId);
        }
    }

    private void getCustomization(String productId) {
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<CustomizationResponse> call = menuInterface.getCustomization(productId);
        call.enqueue(new Callback<CustomizationResponse>() {
            @Override
            public void onResponse(Call<CustomizationResponse> call, Response<CustomizationResponse> response) {
                if (response.body() != null) {
                    if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                        if (response.code() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            FoodCustomizationRecyclerAdapter customizationRecyclerAdapter = new FoodCustomizationRecyclerAdapter(context, response.body().getItems().getCustomization(), name, price, productId);
                            binding.recyclerCustomization.setAdapter(customizationRecyclerAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomizationResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

}
