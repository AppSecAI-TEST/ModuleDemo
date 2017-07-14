package com.hunter.moduledemo.dagger2.module;

import android.app.Activity;

import com.example.modulebaselib.dagger2.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

@Module
public class HomeActivityModule {
    private Activity mActivity;

    public HomeActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
