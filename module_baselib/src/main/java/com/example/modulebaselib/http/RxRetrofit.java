package com.example.modulebaselib.http;


import android.util.Log;

import com.example.modulebaselib.base.BaseApplication;
import com.example.modulebaselib.utils.NetworkUtils;
import com.example.modulebaselib.utils.SDcardUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 对网络请求的封装
 * Created by HeQuanLi on 2016/8/3.
 */
public class RxRetrofit {

    private static OkHttpClient instance = null;
    //定义日志显示级别
    private static HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;

    private RxRetrofit() {
        throw new UnsupportedOperationException("you can't instantiate me...");
    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (RxRetrofit.class) {
                if (instance == null) {
                    instance = new OkHttpClient.Builder()
                            .cache(setCacheSize())
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(setCacheInterceptor())
                            .addInterceptor(setLog().setLevel(level))
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return instance;
    }

//    private static OkHttpClient httpClient = new OkHttpClient.Builder()
//            .cache(setCacheSize())
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(setCacheInterceptor())
//            .addInterceptor(setLog().setLevel(level))
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build();

    /**
     * 设置缓存路径与大小
     *
     * @return
     */
    private static Cache setCacheSize() {
        File cacheFile = new File(SDcardUtils.getDiskCacheDir(BaseApplication.getInstance()), "HeQuanli");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        return cache;
    }

    /***
     * 设置缓存拦截处理
     * @return
     */
    private static Interceptor setCacheInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        return interceptor;
    }


    /**
     * 设置日志输出
     *
     * @return
     */
    private static HttpLoggingInterceptor setLog() {
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("HTTP", "Hunter-->>>" + message);
            }
        });
        return loggingInterceptor;
    }
}
