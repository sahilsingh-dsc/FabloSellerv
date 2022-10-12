package com.myfablo.seller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.WriterException;
import com.myfablo.seller.databinding.ActivityInitBinding;
import com.myfablo.seller.preference.AuthPref;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class InitActivity extends AppCompatActivity {

    private ActivityInitBinding binding;
    private Context context;

    private static final String TAG = "InitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = InitActivity.this;
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
        // Getting QR-Code as Bitmap
        Bitmap bitmap = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        binding.ivQr.setImageBitmap(bitmap);
    }

}