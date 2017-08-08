package com.hunter.modulebaselib.http;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HeQuanli on 2017/8/8.
 * 类说明：
 */

public class RxRetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;

    private OkHttpClient okHttpClient = RxOkHttpClient.getInstance();

    public static String baseUrl = IApi.REQUEST_URL;


    private static RxRetrofitClient mRetrofitInstance;

    private Retrofit mRetrofit;

    public static RxRetrofitClient getInstance() {
        if (mRetrofitInstance == null) {
            synchronized (RxOkHttpClient.class) {
                if (mRetrofitInstance == null) {
                    mRetrofitInstance = new RxRetrofitClient();
                }
            }
        }
        return mRetrofitInstance;
    }


    public static RxRetrofitClient getInstance(String url) {
        mRetrofitInstance = new RxRetrofitClient(url);
        return mRetrofitInstance;
    }

    private RxRetrofitClient() {
        this(null);
    }

    private RxRetrofitClient(String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(url);
        mRetrofit = builder.build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
