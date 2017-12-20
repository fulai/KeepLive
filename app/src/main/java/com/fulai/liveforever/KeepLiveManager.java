package com.fulai.liveforever;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fulai.liveforever.activity.KeepLiveActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dengmao on 17/12/20.
 */

public class KeepLiveManager {
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


}
