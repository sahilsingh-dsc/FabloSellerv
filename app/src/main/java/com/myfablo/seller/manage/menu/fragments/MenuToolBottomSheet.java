package com.myfablo.seller.manage.menu.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.databinding.BottomSheetMenuToolBinding;
import com.myfablo.seller.manage.menu.add.subcategory.AddSubCategoryBottomSheet;
import com.myfablo.seller.manage.menu.addons.fragments.AddonGroupBottomSheet;

import org.greenrobot.eventbus.EventBus;

public class MenuToolBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetMenuToolBinding binding;
    private Context context;
    private String type;
    private String parentId;
    private Boolean hasSubCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetMenuToolBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        if (getArguments() != null) {
            type = getArguments().getString("type");
            parentId = getArguments().getString("id");
            hasSubCategory = getArguments().getBoolean("hasSubCategory");
        }
        initClick();
        showAddableOptions();
    }

    private void initClick() {
        binding.lhAddItem.setOnClickListener(this);
        binding.lhAddCategory.setOnClickListener(this);
        binding.lhSubCategory.setOnClickListener(this);
        binding.lhAddAddOns.setOnClickListener(this);
    }

    private void showAddableOptions() {
        if (type.equals("category")) {
            if (hasSubCategory) {
                showAddSubCategory();
            } else {
                showAddProduct();
            }
        } else if (type.equals("subcategory")) {
            showAddProduct();
        }
    }

    private void showAddProduct() {
        binding.lvSubCategory.setVisibility(View.GONE);
        binding.lvAddItem.setVisibility(View.VISIBLE);
        binding.lvAddCategory.setVisibility(View.GONE);
        binding.lvAddAddOns.setVisibility(View.GONE);
    }

    private void showAddSubCategory() {
        binding.lvSubCategory.setVisibility(View.VISIBLE);
        binding.lvAddItem.setVisibility(View.GONE);
        binding.lvAddCategory.setVisibility(View.GONE);
        binding.lvAddAddOns.setVisibility(View.GONE);
    }

    private void showAddSubCategoryAndProduct() {
        binding.lvSubCategory.setVisibility(View.VISIBLE);
        binding.lvAddItem.setVisibility(View.VISIBLE);
        binding.lvAddCategory.setVisibility(View.GONE);
        binding.lvAddAddOns.setVisibility(View.GONE);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBus.getDefault().post("menuToolReload");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.lhAddAddOns) {
            AddonGroupBottomSheet groupBottomSheet = new AddonGroupBottomSheet();
            groupBottomSheet.show(getChildFragmentManager(), "group");
        } else if (view == binding.lhSubCategory) {
            AddSubCategoryBottomSheet subCategoryBottomSheet = new AddSubCategoryBottomSheet();
            Bundle bundle = new Bundle();
            bundle.putString("id", parentId);
            subCategoryBottomSheet.setArguments(bundle);
            subCategoryBottomSheet.show(getChildFragmentManager(), "subCategory");
        }
    }
}
