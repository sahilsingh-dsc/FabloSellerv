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

public class OrderService extends Service implements View.OnClickListener {

    private WindowManager wm;
    private Button button;

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
            stopForeground(true);
            stopSelf();
        }


        return START_NOT_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        OutletPref outletPref = new OutletPref(getApplicationContext());
        PNConfiguration pnConfiguration = null;
        try {
            pnConfiguration = new PNConfiguration(outletPref.getSellerId());
        } catch (PubNubException e) {
            e.printStackTrace();
        }
        pnConfiguration.setPublishKey("pub-c-40e1c3cd-397d-449b-9a06-2e0505653027");
        pnConfiguration.setSubscribeKey("sub-c-e240b078-b657-4d79-84e1-0504adfe3cf8");
        pnConfiguration.setSecure(false);
        try {
            pnConfiguration.setUuid(outletPref.getSellerId());
        } catch (PubNubException e) {
            e.printStackTrace();
        }
        PubNub pubnub = new PubNub(pnConfiguration);
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(@NotNull PubNub pubnub, @NotNull PNStatus pnStatus) {

            }

            @Override
            public void message(@NotNull PubNub pubnub, @NotNull PNMessageResult message) {
                String messagePublisher = message.getPublisher();
                System.out.println("Message publisher: " + messagePublisher);
                System.out.println("Message Payload: " + message.getMessage());
                EventBus.getDefault().post(message);
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.notification);
                mp.start();
                System.out.println("Message Subscription: " + message.getSubscription());
                System.out.println("Message Channel: " + message.getChannel());
                System.out.println("Message timetoken: " + message.getTimetoken());
            }

            @Override
            public void presence(@NotNull PubNub pubnub, @NotNull PNPresenceEventResult pnPresenceEventResult) {

            }

            @Override
            public void signal(@NotNull PubNub pubnub, @NotNull PNSignalResult pnSignalResult) {

            }

            @Override
            public void uuid(@NotNull PubNub pubnub, @NotNull PNUUIDMetadataResult pnUUIDMetadataResult) {

            }

            @Override
            public void channel(@NotNull PubNub pubnub, @NotNull PNChannelMetadataResult pnChannelMetadataResult) {

            }

            @Override
            public void membership(@NotNull PubNub pubnub, @NotNull PNMembershipResult pnMembershipResult) {

            }

            @Override
            public void messageAction(@NotNull PubNub pubnub, @NotNull PNMessageActionResult pnMessageActionResult) {

            }

            @Override
            public void file(@NotNull PubNub pubnub, @NotNull PNFileEventResult pnFileEventResult) {

            }
        });

        pubnub.subscribe().channels(Arrays.asList("order")).execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onClick(View view) {
        Log.e("OrderService", "onClick: ");
    }
}
