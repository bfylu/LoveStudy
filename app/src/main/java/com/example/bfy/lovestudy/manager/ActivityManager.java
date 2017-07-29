package com.example.bfy.lovestudy.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfy on 17-5-8.
 *
 */

public class ActivityManager {
    //装载activity的容器
    private List<Activity> activities = new ArrayList<>();
    private static ActivityManager activityManager = null;
    private ActivityManager(){}; //私有的构造方法，外部类是不能实例化

    /**
     * 供外部使用ActivityManager的单例对象
     * @return
     */
    public static ActivityManager getInstance(){
        if (activityManager ==null){
            activityManager = new ActivityManager();
        }

        return null;
    }

    /**
     * 添加activity
     */

    public void addActivity(Activity activity){
        activities.add(activity);

    }

    /**
     * 移除
     */
    public void removerActivity(Activity activity){
        if (activities.contains(activity)) {

            activity.finish();
            activities.remove(activity);
        }
    }

    /**
     * 退出
     */
    public void exit(){
        for (Activity activity:activities){
            activity.finish();
        }
    }

}
