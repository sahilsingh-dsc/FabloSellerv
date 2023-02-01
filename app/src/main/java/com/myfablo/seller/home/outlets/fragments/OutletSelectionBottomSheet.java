package com.myfablo.seller.home.outlets.fragments;

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
import com.myfablo.seller.databinding.BottomSheetOutletSelectionBinding;
import com.myfablo.seller.home.outlets.adapters.OutletsRecyclerAdapter;
import com.myfablo.seller.home.outlets.models.OutletItem;
import com.myfablo.seller.home.outlets.models.OutletsResponse;
import com.myfablo.seller.utils.interfaces.OutletInterface;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutletSelectionBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetOutletSelectionBinding binding;
    private Context context;

    private static final String TAG = "OutletSelectionBottom";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetOutletSelectionBinding.inflate(inflater);
        View view = binding.getRoot();
        if (getContext() != null) {
            context = getContext();
            initView();
        }
        return view;
    }

    private void initView() {
        binding.recyclerOutlets.setLayoutManager(new LinearLayoutManager(context));
        getAllOutlets();
    }

    private void getAllOutlets() {
        AuthPref authPref = new AuthPref(context);
        Log.e(TAG, "getAllOutlets: "+authPref.getAuthToken());
        OutletInterface outletInterface = RestClient.getRetrofitFabloInventoryService(context).create(OutletInterface.class);
        Call<OutletsResponse> call = outletInterface.getAllOutlets("Bearer "+authPref.getAuthToken());
        call.enqueue(new Callback<OutletsResponse>() {
            @Override
            public void onResponse(Call<OutletsResponse> call, Response<OutletsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            OutletsRecyclerAdapter outletsRecyclerAdapter = new OutletsRecyclerAdapter(context, response.body().getItems());
                            binding.recyclerOutlets.setAdapter(outletsRecyclerAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutletsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OutletItem outletItem) {
        if (outletItem != null) {
            dismiss();
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
