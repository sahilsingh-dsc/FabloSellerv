package com.myfablo.seller.manage.outlet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityOutletDetailsBinding;
import com.myfablo.seller.preference.OutletPref;

public class OutletDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOutletDetailsBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutletDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OutletDetailsActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        getOutletDetails();
    }

    private void getOutletDetails() {
        OutletPref outletPref = new OutletPref(context);

        // identification
        binding.tvSellerId.setText(outletPref.getSellerId());
        binding.tvOutletId.setText(outletPref.getOutletId());
        binding.tvOutletName.setText(outletPref.getOutletName());

        // locality
        binding.tvOutletArea.setText(outletPref.getOutletArea());
        binding.tvOutletAddress.setText(outletPref.getOutletAddress());

        // category
        if (outletPref.getType().equals("food")) {
            binding.tvOutletType.setText("Food");
        }

        if (outletPref.getIsPureVeg()) {
            binding.tvOutletServing.setText("Pure Veg");
        } else {
            binding.tvOutletServing.setText("Veg & Non Veg");
        }

        // visibility
        if (outletPref.getIsClosed()) {
            binding.tvOutletStatus.setText("Offline");
        } else {
            binding.tvOutletStatus.setText("Online");
        }

        if (outletPref.getIsFeatured()) {
            binding.tvOutletFeatured.setText("Yes");
        } else {
            binding.tvOutletFeatured.setText("No");
        }

        if (outletPref.getIsDiscounted()) {
            binding.tvOutletDiscount.setText("Yes");
        } else {
            binding.tvOutletDiscount.setText("No");
        }

        // timings
        binding.tvOutletTimeMonday.setText(outletPref.getOpeningHours0());
        binding.tvOutletTimeTuesday.setText(outletPref.getOpeningHours1());
        binding.tvOutletTimeWednesday.setText(outletPref.getOpeningHours2());
        binding.tvOutletTimeThursday.setText(outletPref.getOpeningHours3());
        binding.tvOutletTimeFriday.setText(outletPref.getOpeningHours4());
        binding.tvOutletTimeSaturday.setText(outletPref.getOpeningHours5());
        binding.tvOutletSunday.setText(outletPref.getOpeningHours6());

    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}