package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityOnboardBinding;

public class OnboardActivity extends AppCompatActivity {

    private ActivityOnboardBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = OnboardActivity.this;
        initView();
    }

    private void initView() {

    }
}