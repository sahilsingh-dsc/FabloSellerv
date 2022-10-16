package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.databinding.ActivityOnboardBinding;
import com.myfablo.seller.preference.AuthPref;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

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
        generateAuthTokenQR();
    }

    private void generateAuthTokenQR() {
        AuthPref authPref = new AuthPref(context);
        QRGEncoder qrgEncoder = new QRGEncoder(authPref.getAuthToken(), null, QRGContents.Type.TEXT, 500);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        Bitmap bitmap = qrgEncoder.getBitmap();
        binding.ivOnboardingQr.setImageBitmap(bitmap);
    }

}