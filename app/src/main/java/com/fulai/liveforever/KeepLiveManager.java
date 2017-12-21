package com.fulai.liveforever;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.fulai.liveforever.activity.KeepLiveActivity;
import com.fulai.liveforever.service.KeepLiveService;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dengmao on 17/12/20.
 */

public class KeepLiveManager {
    private static final String TAG = "KeepLiveManager";
    private static KeepLiveManager keepLiveManager;
    private Context context;
    private List<WeakReference<Activity>> refActivityList = new ArrayList<>();

    private KeepLiveManager() {
    }

    public static KeepLiveManager getInstance() {
        if (keepLiveManager == null) {
            keepLiveManager = new KeepLiveManager();
        }
        return keepLiveManager;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addLiveActivity(Activity activity) {
        if (context == null) {
            this.context = activity.getApplicationContext();
        }
        WeakReference refActivity = new WeakReference<Activity>(activity);
        refActivityList.add(refActivity);
    }

    public void startLiveActivity() {
        Intent intent = new Intent(context, KeepLiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void finishLiveActivity() {
        for (WeakReference<Activity> refActivity : refActivityList) {
            if (refActivity != null && refActivity.get() != null) {
                Activity activity = refActivity.get();
                activity.finish();
            }
        }
    }

    public void startForground(KeepLiveService keepLiveService, Service service) {
        int id = 1;
        if (keepLiveService != null) {
            Log.i(TAG, "keepLiveService startForground");
            keepLiveService.startForeground(id, new Notification());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                if (service != null) {
                    Log.i(TAG, "Inner service startForground");
                    service.startForeground(id, new Notification());
                    service.stopSelf();
                }
            }
        }
    }


}
