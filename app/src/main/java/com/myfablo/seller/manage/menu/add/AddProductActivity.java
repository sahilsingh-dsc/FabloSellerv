package com.myfablo.seller.manage.menu.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.myfablo.seller.databinding.ActivityAddProductBinding;

public class AddProductActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityAddProductBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = AddProductActivity.this;
        initView();
    }

    private void initView() {
        binding.switchHasVariants.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (checked) {
            binding.lvProductPrice.setVisibility(View.GONE);
        } else {
            binding.lvProductPrice.setVisibility(View.VISIBLE);
        }
    }
}