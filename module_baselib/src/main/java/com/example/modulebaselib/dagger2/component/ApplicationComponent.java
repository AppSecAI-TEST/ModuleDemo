package com.example.modulebaselib.dagger2.component;

import android.content.Context;

import com.example.modulebaselib.dagger2.module.ApplicationModule;
import com.example.modulebaselib.dagger2.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HeQuanli on 2017/7/13.
 * 类说明：
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context context();
}
