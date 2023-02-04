package com.myfablo.seller.manage.menu.variations;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.myfablo.seller.databinding.BottomSheetSubVariantBinding;
import com.myfablo.seller.utils.ResponseFormatter;

import java.util.List;

public class SubVariantBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetSubVariantBinding binding;
    private Context context;
    private Variant variant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetSubVariantBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        initRecycler();
        if (getArguments() != null) {
            Gson gson = new Gson();
            String variantJson = getArguments().getString("variant");
            variant = gson.fromJson(variantJson, Variant.class);
            if (variant != null) {
                showViewData();
            }
        }

    }

    private void initRecycler() {
        binding.recyclerItems.setLayoutManager(new LinearLayoutManager(context));
    }

    private void showViewData() {
        binding.tvVariationName.setText(variant.getVariantDetail().getVariationName());
        binding.tvSelection.setText(new ResponseFormatter(context)
                .getMinMaxSelection(variant.getVariantDetail().getMinSelection(),
                variant.getVariantDetail().getMaxSelection()));
        showVariationList(variant.getVariantDetail().getVariantList());
    }

    private void showVariationList(List<Variant> variantList) {
        VariantRecyclerAdapter variantRecyclerAdapter = new VariantRecyclerAdapter(context, variantList);
        binding.recyclerItems.setAdapter(variantRecyclerAdapter);
    }

}
