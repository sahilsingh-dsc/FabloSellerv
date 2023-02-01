package com.myfablo.seller.manage.menu;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.myfablo.seller.R;
import com.myfablo.seller.common.BasicResponse;
import com.myfablo.seller.databinding.ActivityMenuBinding;
import com.myfablo.seller.manage.menu.adapters.FoodMenuCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.add.fragments.AddCategoryBottomSheet;
import com.myfablo.seller.manage.menu.add.models.AddCategoryRequest;
import com.myfablo.seller.manage.menu.addons.AddonsCategoryRecyclerAdapter;
import com.myfablo.seller.manage.menu.addons.OutletAddOnsContract;
import com.myfablo.seller.manage.menu.addons.OutletAddonsInterface;
import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;
import com.myfablo.seller.manage.menu.fragments.MenuToolBottomSheet;
import com.myfablo.seller.manage.menu.models.FoodMenuResponse;
import com.myfablo.seller.manage.menu.models.StockUpdate;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMenuBinding binding;
    private Context context;

    private OutletAddOnsContract outletAddOnsContract;
    private String type;

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
        initClick();
        initAnimation();
        initContract();
        initRecycler();
        getFullMenu();
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardAddMenu.setOnClickListener(this);
        binding.lvAllItems.setOnClickListener(this);
        binding.lvAddons.setOnClickListener(this);
        binding.btnMenuTool.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerMenu.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initAnimation() {
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
    }

    private void initContract() {
        outletAddOnsContract = new OutletAddOnsContract(context, new OutletAddonsInterface() {
            @Override
            public void onContractProgress() {
                loadData();
                type = "addons";
            }

            @Override
            public void onContractResponse(List<Item> items) {
                showData();
                setAddOns(items);
            }

            @Override
            public void onContractNotFound() {
                noData();
            }

            @Override
            public void onContractFailure() {
                binding.lottieLoading.loop(false);
                showError();
            }
        });
    }

    private void setAddOns(List<Item> items) {
        AddonsCategoryRecyclerAdapter addonsCategoryRecyclerAdapter = new AddonsCategoryRecyclerAdapter(context, items);
        binding.recyclerMenu.setAdapter(addonsCategoryRecyclerAdapter);
    }

    private void getFullMenu() {
        type = "items";
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
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectAllItems() {
        binding.viewAllItems.setVisibility(View.VISIBLE);
        binding.viewAddons.setVisibility(View.INVISIBLE);
        binding.tvAllItems.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvAddons.setTextColor(getResources().getColor(R.color.color_text_description));
        getFullMenu();
    }

    private void selectAddons() {
        binding.viewAddons.setVisibility(View.VISIBLE);
        binding.viewAllItems.setVisibility(View.INVISIBLE);
        binding.tvAddons.setTextColor(getResources().getColor(R.color.color_text_title));
        binding.tvAllItems.setTextColor(getResources().getColor(R.color.color_text_description));
        outletAddOnsContract.getOutletAddOns();
    }

    private void loadData() {
        binding.recyclerMenu.setVisibility(View.GONE);
        binding.lvNoData.setVisibility(View.GONE);
        binding.lottieLoading.setVisibility(View.VISIBLE);
        binding.lottieError.setVisibility(View.GONE);
        binding.lottieLoading.playAnimation();
    }

    private void noData() {
        if (type.equals("items")) {
            binding.tvNoData.setText("There are no menu items in this outlet");
        } else if (type.equals("addons")) {
            binding.tvNoData.setText("There are no addons in this outlet");
        }
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

    private void updateProductStock(StockUpdate stockUpdate) {
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<BasicResponse> call = menuInterface.changeProductStock(stockUpdate.getProductId());
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            retryService();
                        } else if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_NO_DATA) {
                            OhSnapErrorAlert ohSnapErrorAlert = OhSnapErrorAlert.getInstance();
                            ohSnapErrorAlert.showAlert(context, "Stock update can't be performed, Please contact your dedicated manager.");
                        }
                    }
                } else if (response.code() == Constant.HTTP_CLIENT_ERROR) {
                    showError();
                } else if (response.code() == Constant.HTTP_SERVER_ERROR) {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                showError();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("menuToolReload")) {
            if (type.equals("items")) {
                selectAllItems();
            } else if (type.equals("addons")) {
                selectAddons();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddCategoryRequest addCategoryRequest) {
        if (addCategoryRequest != null) {
            addCategory(addCategoryRequest);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StockUpdate stockUpdate) {
        if (stockUpdate != null) {
            updateProductStock(stockUpdate);
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
        } else if (view == binding.lvAllItems) {
            selectAllItems();
        } else if (view == binding.lvAddons) {
            selectAddons();
        } else if (view == binding.btnMenuTool) {
            MenuToolBottomSheet menuToolBottomSheet = new MenuToolBottomSheet();
            menuToolBottomSheet.show(getSupportFragmentManager(), "menuTool");
        }
    }
}