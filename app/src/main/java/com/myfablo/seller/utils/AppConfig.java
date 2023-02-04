package com.myfablo.seller.utils;

public class AppConfig {

    private String adminBaseUrl;
    private String userBaseUrl;
    private String inventoryBaseUrl;
    private String orderBaseUrl;

    public AppConfig() {
    }

    public AppConfig(String adminBaseUrl, String userBaseUrl, String inventoryBaseUrl, String orderBaseUrl) {
        this.adminBaseUrl = adminBaseUrl;
        this.userBaseUrl = userBaseUrl;
        this.inventoryBaseUrl = inventoryBaseUrl;
        this.orderBaseUrl = orderBaseUrl;
    }

    public String getAdminBaseUrl() {
        return adminBaseUrl;
    }

    public void setAdminBaseUrl(String adminBaseUrl) {
        this.adminBaseUrl = adminBaseUrl;
    }

    public String getUserBaseUrl() {
        return userBaseUrl;
    }

    public void setUserBaseUrl(String userBaseUrl) {
        this.userBaseUrl = userBaseUrl;
    }

    public String getInventoryBaseUrl() {
        return inventoryBaseUrl;
    }

    public void setInventoryBaseUrl(String inventoryBaseUrl) {
        this.inventoryBaseUrl = inventoryBaseUrl;
    }

    public String getOrderBaseUrl() {
        return orderBaseUrl;
    }

    public void setOrderBaseUrl(String orderBaseUrl) {
        this.orderBaseUrl = orderBaseUrl;
    }

    public AppConfig getBaseUrl(Integer env) {
        AppConfig appConfig = new AppConfig();
        if (env == Constant.APP_ENV_DEVELOPMENT) {
            appConfig.setAdminBaseUrl(Constant.DEV_FABLO_ADMIN_SERVICE_BASE_URL);
            appConfig.setUserBaseUrl(Constant.DEV_FABLO_USER_SERVICE_BASE_URL);
            appConfig.setInventoryBaseUrl(Constant.DEV_FABLO_INVENTORY_SERVICE_BASE_URL);
            appConfig.setOrderBaseUrl(Constant.DEV_FABLO_ORDER_SERVICE_BASE_URL);
            return appConfig;
        } else if (env == Constant.APP_ENV_STAGING) {
            appConfig.setAdminBaseUrl(Constant.STAGE_FABLO_ADMIN_SERVICE_BASE_URL);
            appConfig.setUserBaseUrl(Constant.STAGE_FABLO_USER_SERVICE_BASE_URL);
            appConfig.setInventoryBaseUrl(Constant.STAGE_FABLO_INVENTORY_SERVICE_BASE_URL);
            appConfig.setOrderBaseUrl(Constant.STAGE_FABLO_ORDER_SERVICE_BASE_URL);
            return appConfig;
        } else if (env == Constant.APP_ENV_PRODUCTION) {
            appConfig.setAdminBaseUrl(Constant.PROD_FABLO_ADMIN_SERVICE_BASE_URL);
            appConfig.setUserBaseUrl(Constant.PROD_FABLO_USER_SERVICE_BASE_URL);
            appConfig.setInventoryBaseUrl(Constant.PROD_FABLO_INVENTORY_SERVICE_BASE_URL);
            appConfig.setOrderBaseUrl(Constant.PROD_FABLO_ORDER_SERVICE_BASE_URL);
            return appConfig;
        }
        return appConfig;
    }

}
