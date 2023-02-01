package com.myfablo.seller.manage.menu.addons;

import com.myfablo.seller.manage.menu.addons.models.addons_get.Item;

import java.util.List;

public interface OutletAddonsInterface {
    void onContractProgress();

    void onContractResponse(List<Item> items);

    void onContractNotFound();

    void onContractFailure();
}