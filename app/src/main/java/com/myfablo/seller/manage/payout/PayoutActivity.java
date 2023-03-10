package com.myfablo.seller.manage.payout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityPayoutBinding;

public class PayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityPayoutBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = PayoutActivity.this;
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