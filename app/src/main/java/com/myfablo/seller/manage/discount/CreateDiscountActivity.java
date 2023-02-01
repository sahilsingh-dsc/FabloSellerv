package com.myfablo.seller.manage.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityCreateDiscountBinding;
import com.myfablo.seller.utils.interfaces.DiscountInterface;
import com.myfablo.seller.manage.discount.models.create.AddDiscountRequest;
import com.myfablo.seller.utils.preference.AuthPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.FabLoading;
import com.myfablo.seller.utils.alerts.SuccessAlert;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDiscountActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateDiscountBinding binding;
    private Context context;
    private String type;
    private int percent;
    private FabLoading fabLoading;
    private SuccessAlert successAlert;

    private static final String TAG = "CreateDiscountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateDiscountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = CreateDiscountActivity.this;
        initView();
    }

    private void initView() {
        fabLoading = FabLoading.getInstance();
        successAlert = SuccessAlert.getInstance();
        binding.ivGoBack.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);

        binding.card10.setOnClickListener(this);
        binding.card15.setOnClickListener(this);
        binding.card20.setOnClickListener(this);
        binding.card25.setOnClickListener(this);
        binding.card30.setOnClickListener(this);
        binding.card35.setOnClickListener(this);
        binding.card40.setOnClickListener(this);
        binding.card45.setOnClickListener(this);
        binding.card50.setOnClickListener(this);
        binding.card60.setOnClickListener(this);
        binding.card70.setOnClickListener(this);
        binding.card80.setOnClickListener(this);

        type = getIntent().getStringExtra("type");
        if (type.equals("flat")) {
            binding.tvTitle.setText("Create Flat Offer");
            binding.lvCapping.setVisibility(View.GONE);
        } else if (type.equals("cap")) {
            binding.tvTitle.setText("Create Cap Offer");
            binding.lvCapping.setVisibility(View.VISIBLE);
        }

        select10Percent();
    }

    private void select10Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 10;
    }

    private void select15Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 15;
    }

    private void select20Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 20;
    }

    private void select25Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 25;
    }

    private void select30Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 30;
    }

    private void select35Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 35;
    }

    private void select40Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 40;
    }

    private void select45Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 45;
    }

    private void select50Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 50;
    }

    private void select60Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 60;
    }

    private void select70Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_title));

        percent = 70;
    }

    private void select80Percent() {
        binding.card10.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv10.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card15.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv15.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card20.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv20.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card25.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv25.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card30.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv30.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card35.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv35.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card40.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv40.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card45.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv45.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card50.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv50.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card60.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv60.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card70.setCardBackgroundColor(getResources().getColor(R.color.white));
        binding.tv70.setTextColor(getResources().getColor(R.color.color_text_title));

        binding.card80.setCardBackgroundColor(getResources().getColor(R.color.color_primary));
        binding.tv80.setTextColor(getResources().getColor(R.color.color_text_for_bg));

        percent = 80;
    }

    private void validateInput() {
        String orderValue = binding.etOrderValue.getText().toString().trim();
        String capValue = binding.etCap.getText().toString().trim();

        if (orderValue.length() == 0 || !TextUtils.isDigitsOnly(orderValue) || orderValue.equals("0")) {
            binding.tiOrderValue.setErrorEnabled(true);
            binding.tiOrderValue.setError("Valid order value is required");
            return;
        }

        if (type.equals("cap")) {
            if (capValue.length() == 0 || !TextUtils.isDigitsOnly(capValue) || capValue.equals("0")) {
                binding.tiCap.setErrorEnabled(true);
                binding.tiCap.setError("Valid capping value is required");
                return;
            }
        }
        AddDiscountRequest addDiscountRequest = new AddDiscountRequest();

        int orderValueInt = Integer.parseInt(orderValue);
        int capValueInt;
        if (capValue.isEmpty()) {
            capValueInt = 0;
        } else {
            capValueInt = Integer.parseInt(capValue);
        }
        addDiscountRequest.setMaxDiscount(capValueInt);


        addDiscountRequest.setDiscountTitle("");
        addDiscountRequest.setPromoCode(getSaltString());
        if (type.equals("cap")) {
            addDiscountRequest.setIsFlatDiscount(false);
        } else if (type.equals("flat")) {
            addDiscountRequest.setIsFlatDiscount(true);
        }
        addDiscountRequest.setMinAmount(orderValueInt);
        addDiscountRequest.setDiscountPercent(percent);

        createOfferDiscount(addDiscountRequest);
    }

    private void createOfferDiscount(AddDiscountRequest addDiscountRequest) {
        fabLoading.showProgress(context);
        AuthPref authPref = new AuthPref(context);
        DiscountInterface discountInterface = RestClient.getRetrofitFabloInventoryService(context).create(DiscountInterface.class);
        Call<BasicResponse> call = discountInterface.createDiscount(authPref.getBearerToken(), addDiscountRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                fabLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            successAlert.showAlert(context, "Offer Submitted","Your discount has been successfully submitted, it will be live once it's approved.",
                                    true, "submitted");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                fabLoading.hideProgress();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("submitted")) {
            onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        } else if (view == binding.card10) {
            select10Percent();
        } else if (view == binding.card15) {
            select15Percent();
        } else if (view == binding.card20) {
            select20Percent();
        } else if (view == binding.card25) {
            select25Percent();
        } else if (view == binding.card30) {
            select30Percent();
        } else if (view == binding.card35) {
            select35Percent();
        } else if (view == binding.card40) {
            select40Percent();
        } else if (view == binding.card45) {
            select45Percent();
        } else if (view == binding.card50) {
            select50Percent();
        } else if (view == binding.card60) {
            select60Percent();
        } else if (view == binding.card70) {
            select70Percent();
        } else if (view == binding.card80) {
            select80Percent();
        } else if (view == binding.btnSubmit) {
            validateInput();
        }
    }
}