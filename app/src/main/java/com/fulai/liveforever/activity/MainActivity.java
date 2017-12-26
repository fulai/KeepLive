package com.fulai.liveforever.activity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fulai.liveforever.KeepLiveManager;
import com.fulai.liveforever.R;
import com.fulai.liveforever.service.JobServiceImpl;
import com.fulai.liveforever.service.KeepLiveService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MainActivity onCreate");
        setContentView(R.layout.activity_main);
//        KeepLiveManager.getInstance().addLiveActivity(this);
        KeepLiveManager.getInstance().init(this);
        Intent intent = new Intent(this, KeepLiveService.class);
        startService(intent);

        startJobService();
    }

    private void startJobService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(), JobServiceImpl.class.getName
                    ())).setPeriodic(2000).setRequiredNetworkType(JobInfo.NETWORK_TYPE_METERED).build();
            jobScheduler.schedule(jobInfo);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jobScheduler.cancelAll();
            }
        },20000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "MainActivity onDestroy");
//        Intent intent = new Intent(this, KeepLiveActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
        super.onDestroy();
    }
}
