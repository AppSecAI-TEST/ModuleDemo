package com.hunter.modulebaselib.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HeQuanli on 2017/8/8.
 * 类说明：Retrofit的公共类
 */

public class RxRetrofitClient {

    private OkHttpClient okHttpClient = RxOkHttpClient.getInstance();

    private static RxRetrofitClient mRetrofitInstance;
    private static Retrofit mRetrofit;

    private RxRetrofitClient() {
    }

    /**
     * 单例，请求地址外部传进来，保证请求不同的主机地址！可以设置默认地址
     * @param baseurl
     * @return
     */
    public static RxRetrofitClient getInstance(String baseurl) {
        if (mRetrofitInstance == null) {
            synchronized (RxOkHttpClient.class) {
                if (mRetrofitInstance == null) {
                    mRetrofitInstance = new RxRetrofitClient(baseurl);
                }
            }
        }
        return mRetrofitInstance;
    }

    private RxRetrofitClient(String baseurl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(baseurl);
        mRetrofit = builder.build();
    }

    /**
     * 创建service
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }
}
