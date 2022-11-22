package com.myfablo.seller.pubnub;

import android.content.Context;
import android.media.MediaPlayer;

import com.myfablo.seller.R;
import com.myfablo.seller.preference.AuthPref;
import com.myfablo.seller.utils.Constant;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
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

public class OrderSubscriber extends SubscribeCallback {

    private Context context;
    private AuthPref authPref;

    public OrderSubscriber(Context context) {
        this.context = context;
    }

    private PubNub pubnubClient;

    public void initPubNubConfig() throws PubNubException {
        authPref = new AuthPref(context);
        PNConfiguration pnConfiguration = new PNConfiguration(new UserId(authPref.getSellerId()));
        pnConfiguration.setPublishKey(Constant.PUBNUB_PUBLISHER_KEY);
        pnConfiguration.setSubscribeKey(Constant.PUBNUB_SUBSCRIBER_KEY);

        pubnubClient = new PubNub(pnConfiguration);
        pubnubClient.addListener(this);
    }

    public void subscribeOrder() {
        pubnubClient.subscribe()
                .channels(Arrays.asList("order-"+authPref.getSellerId())) // subscribe to channels
                .execute();
    }

    public void unSubscribeOrder() {
        pubnubClient.unsubscribe()
                .channels(Arrays.asList("order-"+authPref.getSellerId()))
                .execute();
    }

    @Override
    public void status(@NotNull PubNub pubnub, @NotNull PNStatus pnStatus) {

    }

    @Override
    public void message(@NotNull PubNub pubnub, @NotNull PNMessageResult message) {
        String messagePublisher = message.getPublisher();
        System.out.println("Message publisher: " + messagePublisher);
        System.out.println("Message Payload: " + message.getMessage());
        System.out.println("Message Subscription: " + message.getSubscription());
        System.out.println("Message Channel: " + message.getChannel());
        System.out.println("Message timeToken: " + message.getTimetoken());
        MediaPlayer mp = MediaPlayer.create(context, R.raw.notification);
        mp.start();
        EventBus.getDefault().post(message);
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
}
