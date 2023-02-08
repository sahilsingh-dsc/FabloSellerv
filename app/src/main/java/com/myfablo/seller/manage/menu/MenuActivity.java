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
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.myfablo.seller.manage.menu.models.Menu;
import com.myfablo.seller.manage.menu.models.StockUpdate;
import com.myfablo.seller.utils.Constant;
import com.myfablo.seller.utils.CustomLayoutManager;
import com.myfablo.seller.utils.alerts.OhSnapErrorAlert;
import com.myfablo.seller.utils.interfaces.MenuInterface;
import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.retrofit.RestClient;
import com.softrunapps.paginatedrecyclerview.PaginatedAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMenuBinding binding;
    private Context context;
    private OutletAddOnsContract outletAddOnsContract;
    private String type;
    private Integer pageIndex = 1;
    private FoodMenuCategoryRecyclerAdapter foodMenuCategoryRecyclerAdapter;
    private List<Menu> menuList;

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
        selectAllItems();
    }

    private void initAdapter() {
        menuList = new ArrayList<>();
        foodMenuCategoryRecyclerAdapter = new FoodMenuCategoryRecyclerAdapter(context, menuList);
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
        binding.cardAddMenu.setOnClickListener(this);
        binding.lvAllItems.setOnClickListener(this);
        binding.lvAddons.setOnClickListener(this);
        binding.btnMenuTool.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerMenu.setLayoutManager(new CustomLayoutManager(context));
        binding.recyclerMenu.setItemViewCacheSize(100);
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
        binding.lottieMenuLoader.setVisibility(View.VISIBLE);
        type = "items";
        OutletPref outletPref = new OutletPref(context);
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<FoodMenuResponse> call = menuInterface.getFullMenu(outletPref.getOutletId(), pageIndex);
        call.enqueue(new Callback<FoodMenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodMenuResponse> call, @NonNull Response<FoodMenuResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodMenuResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                binding.lottieLoading.loop(false);
                showError();
            }
        });
    }

    private void setRecyclerData() {
        foodMenuCategoryRecyclerAdapter.notifyDataSetChanged();
        binding.recyclerMenu.setAdapter(foodMenuCategoryRecyclerAdapter);
        showData();
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
        type = "items";
        pageIndex = 1;
        initAdapter();
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFullMenu();
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
    protected void onDestroy() {
        super.onDestroy();
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