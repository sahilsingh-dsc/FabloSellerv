package com.myfablo.seller.manage.menu;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityMenuBinding;
import com.myfablo.seller.interfaces.MenuInterface;
import com.myfablo.seller.manage.menu.adapters.FoodMenuCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.add.fragments.AddCategoryBottomSheet;
import com.myfablo.seller.manage.menu.add.models.AddCategoryRequest;
import com.myfablo.seller.manage.menu.models.FoodMenuResponse;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.retrofit.RestClient;
import com.myfablo.seller.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMenuBinding binding;
    private Context context;

    private static final String TAG = "MenuActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = MenuActivity.this;
        initView();
    }

    private void initView() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardAddMenu.setOnClickListener(this);

        binding.recyclerMenu.setLayoutManager(new LinearLayoutManager(context));
        binding.lottieLoading.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                noData();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        getFullMenu();
    }

    private void getFullMenu() {
        loadData();
        OutletPref outletPref = new OutletPref(context);
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<FoodMenuResponse> call = menuInterface.getFullMenu(outletPref.getOutletId());
        call.enqueue(new Callback<FoodMenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodMenuResponse> call, @NonNull Response<FoodMenuResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            if (response.body().getItems().getMenu().size() == 0) {
                                binding.lottieLoading.loop(false);
                            } else {
                                FoodMenuCategoryRecyclerAdapter foodMenuCategoryRecyclerAdapter = new FoodMenuCategoryRecyclerAdapter(context,
                                        response.body().getItems().getMenu());
                                binding.recyclerMenu.setAdapter(foodMenuCategoryRecyclerAdapter);
                                showData();
                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodMenuResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                binding.lottieLoading.loop(false);
                showError();
            }
        });
    }

    private void addCategory(AddCategoryRequest addCategoryRequest) {
        loadData();
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<BasicResponse> call = menuInterface.addCategory(addCategoryRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(@NonNull Call<BasicResponse> call, @NonNull Response<BasicResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            getFullMenu();
                        } else {
                            Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BasicResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        binding.recyclerMenu.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        binding.lottieNoData.playAnimation();
        binding.lvNoData.setVisibility(View.VISIBLE);
        binding.recyclerMenu.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.GONE);
    }

    private void showData() {
        binding.lottieNoData.cancelAnimation();
        binding.lvNoData.setVisibility(View.GONE);
        binding.recyclerMenu.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerMenu.setVisibility(View.GONE);
        binding.lottieNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.GONE);
        binding.lottieError.setVisibility(View.VISIBLE);
        binding.lottieError.playAnimation();
    }

    private void retryService() {
        getFullMenu();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddCategoryRequest addCategoryRequest) {
        if (addCategoryRequest != null) {
            addCategory(addCategoryRequest);
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
        } else if (view == binding.cardAddMenu) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("subCategory", false);
            AddCategoryBottomSheet addCategoryBottomSheet = new AddCategoryBottomSheet();
            addCategoryBottomSheet.setStyle(STYLE_NORMAL, R.style.DialogStyle);
            addCategoryBottomSheet.setArguments(bundle);
            addCategoryBottomSheet.show(getSupportFragmentManager(), "addCategory");
        }
    }
}