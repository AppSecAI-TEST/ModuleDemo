package com.hunter.modulebaselib.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HeQuanli on 2017/8/8.
 * 类说明：
 */

public class RxRetrofitClient {

    private OkHttpClient okHttpClient = RxOkHttpClient.getInstance();

    private static RxRetrofitClient mRetrofitInstance;

    private RxRetrofitClient() {
    }

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

    public Retrofit RxRetrofitClient(String baseurl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(baseurl);
        return builder.build();
    }
}
