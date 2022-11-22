package com.myfablo.seller.manage.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myfablo.seller.auth.models.details.SellerDetailsResponse;
import com.myfablo.seller.databinding.ActivitySellerKycBinding;
import com.myfablo.seller.interfaces.SellerInterface;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerKycActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySellerKycBinding binding;
    private Context context;

    private static final String TAG = "SellerKycActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellerKycBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = SellerKycActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        getSellerDetails();
    }

    private void getSellerDetails() {
        AuthPref authPref = new AuthPref(context);
        SellerInterface sellerInterface = RestClient.getRetrofitFabloUserService(context).create(SellerInterface.class);
        Call<SellerDetailsResponse> call = sellerInterface.getSellerDetails(authPref.getBearerToken());
        call.enqueue(new Callback<SellerDetailsResponse>() {
            @Override
            public void onResponse(Call<SellerDetailsResponse> call, Response<SellerDetailsResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            setSellerDetails(response.body());
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            OhSnapErrorAlert ohSnapErrorAlert = OhSnapErrorAlert.getInstance();
                            ohSnapErrorAlert.showAlert(context, "Something went wrong, we were not able to find seller details.");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SellerDetailsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void setSellerDetails(SellerDetailsResponse sellerDetails) {
        binding.tvSellerId.setText(sellerDetails.getItems().getSellerId());
        binding.tvSellerName.setText(sellerDetails.getItems().getBasicDetails().getSellerName());
        binding.tvSellerTradeName.setText(sellerDetails.getItems().getBasicDetails().getSellerName());
        binding.tvSellerPanNumber.setText(sellerDetails.getItems().getBasicDetails().getPanNumber());


        if (sellerDetails.getItems().getBasicDetails().getSellerType().equals("enterprise")) {
            binding.tvSellerType.setText("Enterprise");
        } else if (sellerDetails.getItems().getBasicDetails().getSellerType().equals("individual")) {
            binding.tvSellerType.setText("Individual");
        }
        if (sellerDetails.getItems().getBasicDetails().getIsGst()) {
            binding.tvGstStatus.setText("Yes");
            binding.tvGstNumber.setText(sellerDetails.getItems().getBasicDetails().getGstNo());
        } else {
            binding.tvGstStatus.setText("No");
        }

        binding.tvLicenceType.setText("FSSAI");
        binding.tvLicenceNo.setText(sellerDetails.getItems().getLicenseDetails().getLicenceNumber());
        binding.tvNameOnLicence.setText(sellerDetails.getItems().getLicenseDetails().getNameOnlicence());
        binding.tvLicenceIssued.setText(sellerDetails.getItems().getLicenseDetails().getIssuedOn());
        binding.tvLicenceTenure.setText(sellerDetails.getItems().getLicenseDetails().getTenure());

        binding.tvAuthPersonName.setText(sellerDetails.getItems().getAuthorizedPersonDetails().getName());
        binding.tvPan.setText(sellerDetails.getItems().getAuthorizedPersonDetails().getPan());
        binding.tvPhone.setText(sellerDetails.getItems().getAuthorizedPersonDetails().getPhone());
        binding.tvEmail.setText(sellerDetails.getItems().getAuthorizedPersonDetails().getEmail());
    }

    @Override
    public void onClick(View view) {
        if (view == binding.ivGoBack) {
            onBackPressed();
        }
    }
}