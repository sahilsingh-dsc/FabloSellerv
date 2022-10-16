package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityLaunchBinding;
import com.myfablo.seller.home.HomeActivity;
import com.myfablo.seller.preference.AuthPref;

public class LaunchActivity extends AppCompatActivity {

    private ActivityLaunchBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaunchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = LaunchActivity.this;
        initView();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkAuthStatus();
            }
        }, 1000);

    }

    private void checkAuthStatus() {
        AuthPref authPref = new AuthPref(context);
        if (!authPref.getAuthToken().equals("none")) {
            if (authPref.getOnboardStatus()) {
                gotoHomeScreen();
            } else {
                gotoOnboardScreen();
            }
        } else {
            gotoWelcomeScreen();
        }
    }

    private void gotoHomeScreen() {
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoWelcomeScreen() {
        Intent intent = new Intent(context, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoOnboardScreen() {
        Intent intent = new Intent(context, OnboardActivity.class);
        startActivity(intent);
        finish();
    }

}