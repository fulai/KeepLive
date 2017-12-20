package com.fulai.liveforever.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fulai.liveforever.KeepLiveManager;
import com.fulai.liveforever.R;
import com.fulai.liveforever.service.KeepLiveService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"MainActivity onCreate");
        setContentView(R.layout.activity_main);
//        KeepLiveManager.getInstance().addLiveActivity(this);
        KeepLiveManager.getInstance().init(this);
        Intent intent = new Intent(this, KeepLiveService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"MainActivity onDestroy");
        Intent intent = new Intent(this, KeepLiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onDestroy();
    }
}
