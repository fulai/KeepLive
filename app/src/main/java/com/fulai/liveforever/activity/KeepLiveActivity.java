package com.fulai.liveforever.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.fulai.liveforever.KeepLiveManager;

/**
 * Created by Dengmao on 17/12/20.
 */

public class KeepLiveActivity extends AppCompatActivity {
    private static final String TAG = "KeepLiveActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "KeepLiveActivity onCreate");
        KeepLiveManager.getInstance().addLiveActivity(this);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "KeepLiveActivity onDestroy");
    }
}
