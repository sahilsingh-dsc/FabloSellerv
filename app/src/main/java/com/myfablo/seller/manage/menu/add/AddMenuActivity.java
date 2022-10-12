package com.myfablo.seller.manage.menu.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityAddMenuBinding;

public class AddMenuActivity extends AppCompatActivity {

    private ActivityAddMenuBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = AddMenuActivity.this;
        initView();
    }

    private void initView() {

    }
}