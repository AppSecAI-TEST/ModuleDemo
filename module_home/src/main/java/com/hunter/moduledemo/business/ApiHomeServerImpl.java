package com.hunter.moduledemo.business;

import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.modulebaselib.http.RxOkHttpClient;
import com.hunter.modulebaselib.http.RxRetrofitClient;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.bean.NowHeWeather5Bean;
import com.hunter.moduledemo.mvp.bean.WeatherResultBean;
import com.hunter.moduledemo.utils.HomeConstantUtils;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public class ApiHomeServerImpl {

    private static RxRetrofitClient instance;


    private static IApiHomeServer mMeiZhiServer = new Retrofit.Builder()
            .baseUrl(HomeConstantUtils.REQUEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(RxOkHttpClient.getInstance())
            .build()
            .create(IApiHomeServer.class);

    private static IApiHomeServer mNowWeatherServer = new Retrofit.Builder()
            .baseUrl(HomeConstantUtils.WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(RxOkHttpClient.getInstance())
            .build()
            .create(IApiHomeServer.class);


    public static Observable<ResultBean<List<MeiZhiBean>>> requestMeiZhiData(String category, int num, int page) {
        instance = RxRetrofitClient.getInstance(HomeConstantUtils.REQUEST_URL);
        return instance.getRetrofit().create(IApiHomeServer.class).getMeiZhiList(category, num, page);
    }

    public static Observable<WeatherResultBean<List<NowHeWeather5Bean>>> requestNowWeatherData(String category,
                                                                                               String city, String key) {
        return mNowWeatherServer.getNowWeather( category,city, key);
    }
}
