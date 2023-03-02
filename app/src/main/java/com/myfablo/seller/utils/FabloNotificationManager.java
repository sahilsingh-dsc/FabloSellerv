package com.myfablo.seller.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import com.myfablo.seller.R;

public class FabloNotificationManager {

    private Context context;
    private MediaPlayer mediaPlayer;

    private static final String TAG = "FabloNotification";
    public FabloNotificationManager(Context context) {
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.notification);
    }

    public void startAlertSound() {
        startMediaPlayer();
    }

    public void stopAlertSound() {
        mediaPlayer.stop();
    }

    private void startMediaPlayer() {
        new ExtraUtils(context).getLongHapticFeedback();
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
}
