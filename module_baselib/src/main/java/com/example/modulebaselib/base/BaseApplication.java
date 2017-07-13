package com.example.modulebaselib.base;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.modulebaselib.BuildConfig;
import com.example.modulebaselib.dagger2.component.ApplicationComponent;
import com.example.modulebaselib.dagger2.component.DaggerApplicationComponent;
import com.example.modulebaselib.dagger2.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hunter on 2017/7/12.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    private Set<Activity> allActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initARouter();
        initLeakCanary();
    }
    /**
     * 获取一个全局的应用程序实例
     *
     * @return
     */
    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    private void initARouter() {

        if (!BuildConfig.isBuildModule) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static ApplicationComponent getAppComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(instance))
                .build();
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /**
     * 溢出一个Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    /**
     * 退出APP，销毁所有Activity，并且杀死进程
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity activity : allActivities) {
                    activity.finish();
                }
            }
        }
        allActivities.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
