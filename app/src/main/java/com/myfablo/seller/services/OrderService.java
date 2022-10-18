package com.myfablo.seller.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.myfablo.seller.R;
import com.myfablo.seller.home.HomeActivity;
import com.myfablo.seller.preference.OutletPref;
import com.myfablo.seller.pubnub.OrderSubscriber;
import com.myfablo.seller.utils.Constant;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class OrderService extends Service {

    private OrderSubscriber orderSubscriber;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String sellerId = intent.getStringExtra("sellerId");
        String status = intent.getStringExtra("status");
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);
        }

        Notification notification = new NotificationCompat.Builder(this, Constant.NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Looking Order")
                .setContentText(sellerId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .build();

        if (status.equals("start")) {
            startForeground(1, notification);
        } else if (status.equals("stop")) {
            orderSubscriber.unSubscribeOrder();
            stopForeground(true);
            stopSelf();
        }

        return START_NOT_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        orderSubscriber = new OrderSubscriber();
        try {
            orderSubscriber.initPubNubConfig();
            orderSubscriber.subscribeOrder();
        } catch (PubNubException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderSubscriber.unSubscribeOrder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
