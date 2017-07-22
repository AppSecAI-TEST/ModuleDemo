package com.hunter.moduledemo.dagger2.component;

import android.app.Activity;

import com.hunter.modulebaselib.dagger2.component.ApplicationComponent;
import com.hunter.modulebaselib.dagger2.scopes.ActivityScope;
import com.hunter.moduledemo.dagger2.module.HomeActivityModule;
import com.hunter.moduledemo.mvp.view.HomeActivity;

import dagger.Component;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = HomeActivityModule.class)
public interface HomeActivityComponent {

    Activity getActivity();

    void inject(HomeActivity activity);
}