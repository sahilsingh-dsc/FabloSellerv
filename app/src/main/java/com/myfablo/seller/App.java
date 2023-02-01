package com.myfablo.seller;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.myfablo.seller.utils.preference.OutletPref;
import com.myfablo.seller.utils.Constant;
import com.onesignal.OneSignal;

public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        initOneSignal();
        initServiceNotificationChannel();
    }

    private void initOneSignal() {
        OutletPref outletPref = new OutletPref(getApplicationContext());
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(Constant.ONESIGNAL_APP_ID);
        OneSignal.setExternalUserId(outletPref.getSellerId());
        OneSignal.promptForPushNotifications();
    }

    private void initServiceNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationServiceChannel = new NotificationChannel(
                    Constant.NOTIFICATION_CHANNEL_ID,
                    Constant.NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationServiceChannel);
        }
    }

}
