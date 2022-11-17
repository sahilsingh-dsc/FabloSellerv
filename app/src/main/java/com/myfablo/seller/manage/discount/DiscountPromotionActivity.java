package com.myfablo.seller.manage.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityDiscountPromotionBinding;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;

public class DiscountPromotionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDiscountPromotionBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscountPromotionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = DiscountPromotionActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardCampaign.setOnClickListener(this);
        binding.cardManageCampaign.setOnClickListener(this);
        binding.lvCreateOffer.setOnClickListener(this);
        binding.lvRunningOffer.setOnClickListener(this);
        binding.cardDiscountCap.setOnClickListener(this);
        binding.cardDiscountFlat.setOnClickListener(this);
        selectCreateOffer();
    }

    private void selectCreateOffer() {
        binding.viewCreateOffer.setVisibility(View.VISIBLE);
        binding.viewRunningOffer.setVisibility(View.INVISIBLE);
        binding.tvCreateOffer.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvRunningOffer.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.scrollCreate.setVisibility(View.VISIBLE);
        binding.frameRunningOffer.setVisibility(View.GONE);
    }

    private void selectRunningOffer() {
        binding.viewRunningOffer.setVisibility(View.VISIBLE);
        binding.viewCreateOffer.setVisibility(View.INVISIBLE);
        binding.tvRunningOffer.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvCreateOffer.setTextColor(getResources().getColor(R.color.color_text_description));
        binding.scrollCreate.setVisibility(View.GONE);
        binding.frameRunningOffer.setVisibility(View.VISIBLE);
    }

    private void gotoCreateDiscountScreen(String type) {
        Intent intent = new Intent(context, CreateDiscountActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.cardDiscountCap) {
            gotoCreateDiscountScreen("cap");
        } else if (view == binding.cardDiscountFlat) {
            gotoCreateDiscountScreen("flat");
        } else if (view == binding.lvCreateOffer) {
            selectCreateOffer();
        } else if (view == binding.lvRunningOffer) {
            selectRunningOffer();
        }
    }
}