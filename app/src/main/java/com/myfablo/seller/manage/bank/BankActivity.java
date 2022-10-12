package com.myfablo.seller.manage.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityBankBinding;

public class BankActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityBankBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBankBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = BankActivity.this;
        intiView();
    }

    private void intiView() {
        binding.ivGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}