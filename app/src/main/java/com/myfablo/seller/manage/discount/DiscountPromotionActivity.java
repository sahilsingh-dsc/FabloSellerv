package com.myfablo.seller.manage.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityDiscountPromotionBinding;

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
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}