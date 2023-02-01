package com.myfablo.seller.manage.menu.add.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.databinding.BottomSheetAddMenuCategoryBinding;
import com.myfablo.seller.manage.menu.add.models.AddCategoryRequest;
import com.myfablo.seller.utils.preference.OutletPref;

import org.greenrobot.eventbus.EventBus;

public class AddCategoryBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener, TextWatcher {

    private BottomSheetAddMenuCategoryBinding binding;
    private Context context;

    private static final String TAG = "AddCategoryBottomSheet";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetAddMenuCategoryBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            intiView();
        }
        return view;
    }

    private void intiView() {
        if (getArguments() != null) {
            boolean isSubCategory = getArguments().getBoolean("subCategory");
            if (isSubCategory) {
                binding.tvCategoryTitle.setText("Add Sub Category");
                binding.tvCategoryDescription.setText("Add sub category and add products with ease");
                binding.btnAddCategory.setText("Add Sub Category");
            } else {
                binding.tvCategoryTitle.setText("Add Category");
                binding.tvCategoryDescription.setText("Add category and add products or sub-categories with ease");
                binding.btnAddCategory.setText("Add Category");
            }
        }
        binding.btnAddCategory.setOnClickListener(this);
        binding.etCategoryName.addTextChangedListener(this);
    }

    private void validateInput() {
        if (getArguments() != null) {
            boolean isSubCategory = getArguments().getBoolean("subCategory");
            String categoryId = getArguments().getString("categoryId");
            String categoryName = binding.etCategoryName.getText().toString().trim();
            if (categoryName.isEmpty()) {
                if (isSubCategory) {
                    binding.tvCategoryNameError.setText("Sub category name cannot be empty");
                    binding.tvCategoryNameError.setVisibility(View.VISIBLE);
                } else {
                    binding.tvCategoryNameError.setText("Category name cannot be empty");
                    binding.tvCategoryNameError.setVisibility(View.VISIBLE);
                }
                return;
            }
            AddCategoryRequest addCategoryRequest = new AddCategoryRequest();
            OutletPref outletPref = new OutletPref(context);
            if (isSubCategory) {
                addCategoryRequest.setParentCategoryId(categoryId);
            }
            addCategoryRequest.setCategoryName(categoryName);
            addCategoryRequest.setOutletId(outletPref.getOutletId());
            EventBus.getDefault().post(addCategoryRequest);
            dismiss();
        }

    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnAddCategory) {
            validateInput();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        binding.tvCategoryNameError.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
