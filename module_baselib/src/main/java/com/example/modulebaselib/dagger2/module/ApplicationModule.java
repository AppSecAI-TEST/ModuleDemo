package com.example.modulebaselib.dagger2.module;

import android.app.Application;
import android.content.Context;

import com.example.modulebaselib.dagger2.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HeQuanli on 2017/7/13.
 * 类说明：
 */

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideApplicationContext() {
        return this.application;
    }


}
