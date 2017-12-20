package com.fulai.liveforever.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fulai.liveforever.KeepLiveManager;

/**
 * Created by Dengmao on 17/12/20.
 */

public class KeepLiveReceiver extends BroadcastReceiver {

    private static final String TAG = "receive";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            Log.i(TAG, "screen_off");
            KeepLiveManager.getInstance().startLiveActivity();
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            Log.i(TAG, "screen_on");
            KeepLiveManager.getInstance().finishLiveActivity();
        }
    }
}
