package com.myfablo.seller.manage.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.myfablo.seller.R;
import com.myfablo.seller.databinding.ActivityDiscountPromotionBinding;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;

public class DiscountPromotionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDiscountPromotionBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscountPromotionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = DiscountPromotionActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardPromo.setOnClickListener(this);
        binding.cardBogo.setOnClickListener(this);
        binding.cardFlatPercent.setOnClickListener(this);
        binding.cardFlatAmount.setOnClickListener(this);
        binding.cardFreebies.setOnClickListener(this);
        binding.cardCampaign.setOnClickListener(this);
        binding.cardManageCampaign.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.cardPromo) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardBogo) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardFlatPercent) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardFlatAmount) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardFreebies) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardCampaign) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        } else if (view == binding.cardManageCampaign) {
            OhSnapErrorAlert alert = OhSnapErrorAlert.getInstance();
            alert.showAlert(context, getString(R.string.str_err_disabled));
        }
    }
}