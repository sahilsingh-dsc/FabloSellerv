package com.myfablo.seller.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.myfablo.seller.R;

public class FabloNotificationManager {

    private Context context;
    private MediaPlayer mediaPlayer;
    private Handler handler;

    public FabloNotificationManager(Context context) {
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.notification);
        handler = new Handler();
    }

    public void startAlertSound() {
        initLooper();
    }

    public void stopAlertSound() {
        stopMediaPlayer();
        handler = null;
    }

    private void initLooper() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getHapticFeedBack();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
        startMediaPlayer();
    }

    private void startMediaPlayer() {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    private void stopMediaPlayer() {
        mediaPlayer.stop();
    }

    private void getHapticFeedBack() {
        new ExtraUtils(context).getHapticFeedback();
    }

}
