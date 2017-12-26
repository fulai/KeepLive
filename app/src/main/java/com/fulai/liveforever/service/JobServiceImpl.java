package com.fulai.liveforever.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Dengmao on 17/12/23.
 * (1) false：框架认为你作业已经执行完毕了，那么下一个作业就立刻展开了
 * (2) true：框架将作业结束状态交给你去处理。因为我们可能会异步的通过线程等方式去执行工作，
 * 这个时间肯定不能放在主线程里面去控制，这时候需要手动调用jobFinished(JobParameters params,
 * boolean needsReschedule)方法去告诉框架作业结束了，其中needsReschedule表示是否重复执行
 * <p>
 * 当你使用cancel()或者cancelAll()的话会执行onStopJob方法。有个地方要注意，
 * 如果你onStartJob返回的是false的话，系统会因为认为工作已经结束而不再产生onStopJob回调
 *
 * @author Dengmao
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobServiceImpl extends JobService {


    private static final String TAG = "JobServiceImpl";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /**
     * 主线程上
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStartJob(final JobParameters params) {
        String thread = Thread.currentThread().toString();
        Log.i(TAG, "onStartJob:" + thread);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jobFinished(params, false);
            }
        }, 5000);
        return false;
    }

    /**
     * 主线程上
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        String thread = Thread.currentThread().toString();
        Log.i(TAG, "onStopJob:" + thread);
        return false;
    }
}
