package com.myfablo.seller.manage.support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivitySupportBinding;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySupportBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SupportActivity.this;
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