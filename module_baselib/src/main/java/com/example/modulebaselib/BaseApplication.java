package com.example.modulebaselib;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Hunter on 2017/7/12.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        
        if (!BuildConfig.isBuildModule){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
