package com.myfablo.seller.manage.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityOutletAddonsBinding;
import com.myfablo.seller.manage.menu.addons.AddonsCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.addons.OutletAddOnsContract;
import com.myfablo.seller.manage.menu.addons.OutletAddonsInterface;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;

import java.util.List;

public class OutletAddonsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOutletAddonsBinding binding;
    private Context context;
    private OutletAddOnsContract outletAddOnsContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutletAddonsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OutletAddonsActivity.this;
        initView();
    }

    private void initView() {
        initClick();
        initRecycler();
        initContract();
        outletAddOnsContract.getOutletAddOns();
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerMenu.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initContract() {
        outletAddOnsContract = new OutletAddOnsContract(context, new OutletAddonsInterface() {
            @Override
            public void onContractProgress() {

            }

            @Override
            public void onContractResponse(List<Item> items) {
                showAddonsList(items);
            }

            @Override
            public void onContractNotFound() {
                noData();
            }

            @Override
            public void onContractFailure() {

            }
        });
    }

    private void showAddonsList(List<Item> items) {
        AddonsCategoryRecyclerAdapter adapter = new AddonsCategoryRecyclerAdapter(context, items);
        binding.recyclerMenu.setAdapter(adapter);
        showData();
    }

    private void showData() {
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerMenu.setVisibility(View.VISIBLE);
    }

    private void noData() {
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerMenu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}