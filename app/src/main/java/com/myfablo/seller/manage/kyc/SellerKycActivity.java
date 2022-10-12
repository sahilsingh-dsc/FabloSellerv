package com.myfablo.seller.manage.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivitySellerKycBinding;

public class SellerKycActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySellerKycBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellerKycBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SellerKycActivity.this;
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