package com.myfablo.seller.manage.menu.add.subcategory;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.BottomSheetAddMenuSubcategoryBinding;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;
import com.myfablo.seller.utils.alerts.SuccessAlert;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.retrofit.RestClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubCategoryBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private static final String TAG = "AddSubCategoryBottom";
    private BottomSheetAddMenuSubcategoryBinding binding;
    private Context context;
    private String parentId;
    private FabLoading fabLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetAddMenuSubcategoryBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        if (getArguments() != null) {
            parentId = getArguments().getString("id");
        }
        fabLoading = FabLoading.getInstance();
        initClick();
        initTextWatcher();
    }

    private void initTextWatcher() {
        binding.etCategoryName.addTextChangedListener(new TextWatcher() {
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
        });
    }

    private void initClick() {
        binding.btnAddSubCategory.setOnClickListener(this);
    }

    private void validateInput() {
        String subCategory = binding.etCategoryName.getText().toString().trim();
        if (subCategory.isEmpty()) {
            binding.tvCategoryNameError.setText("Sub category name required");
            binding.tvCategoryNameError.setVisibility(View.VISIBLE);
            return;
        }
        addSubCategory(subCategory);
    }

    private void addSubCategory(String subCategory) {
        fabLoading.showProgress(context);
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        menuInterface.addSubCategory(new AddSubCategoryRequest(parentId, subCategory))
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BasicResponse> call, @NonNull Response<BasicResponse> response) {
                        fabLoading.hideProgress();
                        if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                            if (response.body() != null) {
                                if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                                    SuccessAlert.getInstance().showAlert(context,
                                            "Sub Category Added",
                                            "Sub category has been added to category now you can add products.",
                                            true, "addSubCategory");
                                } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                                    OhSnapErrorAlert.getInstance().showAlert(context, "Something went wrong from our side, Please try again.");
                                }
                            }
                        } else if (response.code() == Constant.HTTP_SERVER_ERROR) {
                            OhSnapErrorAlert.getInstance().showAlert(context, "Something went wrong from our side, Please try again.");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BasicResponse> call, @NonNull Throwable t) {
                        fabLoading.hideProgress();
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("addSubCategory")) {
            dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnAddSubCategory) {
            validateInput();
        }
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
