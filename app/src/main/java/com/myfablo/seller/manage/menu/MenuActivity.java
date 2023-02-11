package com.myfablo.seller.manage.menu;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
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
    private Integer pageIndex = 1;
    private Integer pageLimit;
    private int visibleThreshold = 5;
    private int lastVisibleItem;
    private int totalItemCount;
    private boolean isLoading = false;
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
        menuList = new ArrayList<>();
        initClick();
        initRecycler();
    }

    private void initClick() {
        binding.ivGoBack.setOnClickListener(this);
        binding.btnAddOns.setOnClickListener(this);
    }

    private void initRecycler() {
        binding.recyclerMenu.setLayoutManager(new LinearLayoutManager(context));
        foodMenuCategoryRecyclerAdapter = new FoodMenuCategoryRecyclerAdapter(context);
        binding.recyclerMenu.setAdapter(foodMenuCategoryRecyclerAdapter);

        binding.recyclerMenu.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerMenu.getLayoutManager();
            assert linearLayoutManager != null;
            totalItemCount = linearLayoutManager.getItemCount();
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (pageIndex <= pageLimit) {
                        isLoading = true;
                        pageIndex++;
                        getFullMenu();
                        isLoading = false;
                    }
                }
        });
    }

    private void getFullMenu() {
        OutletPref outletPref = new OutletPref(context);
        MenuInterface menuInterface = RestClient.getRetrofitFabloInventoryService(context).create(MenuInterface.class);
        Call<FoodMenuResponse> call = menuInterface.getFullMenu(outletPref.getOutletId(), pageIndex);
        call.enqueue(new Callback<FoodMenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodMenuResponse> call, @NonNull Response<FoodMenuResponse> response) {
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    if (response.body() != null) {
                        if (response.body().getSubCode() == Constant.SERVICE_RESPONSE_CODE_SUCCESS) {
                            pageLimit = response.body().getItems().getTotalPage();
                            menuList.addAll(response.body().getItems().getMenu());
                            foodMenuCategoryRecyclerAdapter.submitList(menuList);
                            binding.recyclerMenu.scrollToPosition(lastVisibleItem);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodMenuResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void retryService() {
        getFullMenu();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        if (data.equals("menuToolReload")) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddCategoryRequest addCategoryRequest) {
        if (addCategoryRequest != null) {
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
        } else if (view == binding.btnAddOns) {
            Intent intent = new Intent(context, OutletAddonsActivity.class);
            startActivity(intent);
        }
    }
}