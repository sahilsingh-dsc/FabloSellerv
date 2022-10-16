package com.myfablo.seller.utils;

public class Constant {

    public static final String YELO_API_KEY = "a84b3b1809db536e3684b00dc7d26e93";
    public static final String MP_ACCESS_TOKEN = "abc67e448d6a00ae9d1dabcf55b83509";
    public static final int MP_USER_ID = 1183512;
    public static final int MP_USER_ACCESS_TYPE_MERCHANT = 3;
    public static final int MP_USER_ACCESS_TYPE_ADMIN = 2;

    public static final String FABLO_USER_TYPE_SELLER = "seller";
    public static final String PREF_AUTH = "auth";
    public static final String PREF_INIT = "init";
    public static final String PREF_OUTLET = "outlet";
    public static final String PREF_ORDER_SERVICE = "orderService";
    public static final String PREF_SELECTED_OPTION = "selectedOption";

    public static final String LOGIN_TYPE_SELLER = "seller";
    public static final String LOGIN_TYPE_OUTLET = "outlet";

    public static final int OUTLET_STATUS_MODE_OFFLINE = 0;
    public static final int OUTLET_STATUS_MODE_ONLINE = 1;
    public static final int OUTLET_STATUS_MODE_ALL = 2;

    public static final int HTTP_RESOURCE_CREATED = 201;
    public static final int HTTP_RESPONSE_SUCCESS = 200;
    public static final int HTTP_CLIENT_ERROR = 400;
    public static final int HTTP_RESOURCE_FORBIDDEN = 403;
    public static final int HTTP_RESOURCE_NOT_FOUND = 404;
    public static final int HTTP_SERVER_ERROR = 500;
    public static final int HTTP_SERVER_MAINTENANCE = 503;

    public static final int SERVICE_RESPONSE_CODE_SUCCESS = 200;
    public static final int SERVICE_RESPONSE_CODE_NO_DATA = 400;

    public static final String ORDER_STATUS_INIT = "init";
    public static final String ORDER_STATUS_ACCEPTED = "accepted";
    public static final String ORDER_STATUS_CANCELLED = "cancelled";
    public static final String ORDER_STATUS_PREPARING = "preparing";
    public static final String ORDER_STATUS_READY = "ready";
    public static final String ORDER_STATUS_DISPATCHED = "dispatched";
    public static final String ORDER_STATUS_DELIVERED = "delivered";

    public static final String SELECTED_OUTLET_OPTION_OFFLINE = "offline";
    public static final String SELECTED_OUTLET_OPTION_ONLINE = "online";

    public static final String SELECTED_ORDER_STATUS_OPTION_PREPARING = "preparing";
    public static final String SELECTED_ORDER_STATUS_OPTION_READY = "ready";
    public static final String SELECTED_ORDER_STATUS_OPTION_DISPATCHED = "dispatched";
    public static final String SELECTED_ORDER_STATUS_OPTION_DELIVERED = "delivered";
    public static final String SELECTED_ORDER_STATUS_OPTION_CANCELLED = "cancelled";

    public static final int ORDER_SORT_COL_BY_ORDER_ID = 1;
    public static final int ORDER_SORT_COL_BY_DELIVERY_DATE_TIME = 3;
    public static final String ORDER_SORT_DIR_DESC = "desc";

    public static final int ORDER_STATUS_ACCEPT = 1;
    public static final int ORDER_STATUS_REJECT = 0;

    public static final int REQUEST_SET_OFF_TRUE = 1;
    public static final int REQUEST_SET_OFF_FALSE = 0;

    public static final int STATUS_TRUE = 1;
    public static final int STATUS_FALSE = 0;

    public static final String NOTIFICATION_CHANNEL_ID = "orderService";
    public static final String NOTIFICATION_CHANNEL_NAME = "Order Service";

    public static final String ONESIGNAL_APP_ID = "73beb105-9dca-45eb-8f7c-479ddc15cb43";



}
