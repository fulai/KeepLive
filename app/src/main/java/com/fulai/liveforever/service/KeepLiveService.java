package com.fulai.liveforever.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fulai.liveforever.KeepLiveManager;
import com.fulai.liveforever.broadcast.KeepLiveReceiver;

/**
 * 提升服务进程优先级
 *
 */
public class KeepLiveService extends Service {
    private static final String TAG = "KeepLiveService";
    private KeepLiveReceiver keepLiveReceiver;
    private static KeepLiveService mKeepLiveService;

    public KeepLiveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mKeepLiveService = this;
        Log.i(TAG, "KeepLiveService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "KeepLiveService onStartCommand");
        keepLiveReceiver = new KeepLiveReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(keepLiveReceiver, intentFilter);
        Intent intentService = new Intent(this, InnerService.class);
        startService(intentService);
//        startForeground(1,new Notification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(keepLiveReceiver);
    }

    public static class InnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            KeepLiveManager.getInstance().startForground(mKeepLiveService, this);
            return super.onStartCommand(intent, flags, startId);
        }
    }

}
