package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowInsets;

import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityWelcomeBinding;
import com.myfablo.seller.utils.Constant;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityWelcomeBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setTheme(R.style.Theme_FabloTrans);
        setTransparentStatusBar();
        View view = binding.getRoot();
        setContentView(view);
        context = WelcomeActivity.this;
        initView();
    }

    private void setTransparentStatusBar() {
        View decorView = getWindow().getDecorView();
        decorView.setOnApplyWindowInsetsListener((v, insets) -> {
            WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
            return defaultInsets.replaceSystemWindowInsets(
                    defaultInsets.getSystemWindowInsetLeft(),
                    0,
                    defaultInsets.getSystemWindowInsetRight(),
                    defaultInsets.getSystemWindowInsetBottom());
        });
        ViewCompat.requestApplyInsets(decorView);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void initView() {
        binding.btnOutletLogin.setOnClickListener(this);
        binding.btnSellerLogin.setOnClickListener(this);
        showPolicyLink();
    }

    private void showPolicyLink() {
        String policy = "<font color=#7E8387>Moving forward you will accept </font> <font color=#00C49D>Fablo Seller Terms and Conditions</font> <font color=#7E8387> & </font> <font color=#00C49D>GDPR privacy policy.</font>";
        binding.tvPolicyCheck.setText(Html.fromHtml(policy));
    }

    private void gotoAuthScreen(String type) {
        Intent intent = new Intent(context, AuthActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnOutletLogin) {
            gotoAuthScreen(Constant.LOGIN_TYPE_OUTLET);
        } else if (view == binding.btnSellerLogin) {
            gotoAuthScreen(Constant.LOGIN_TYPE_SELLER);
        }
    }
}