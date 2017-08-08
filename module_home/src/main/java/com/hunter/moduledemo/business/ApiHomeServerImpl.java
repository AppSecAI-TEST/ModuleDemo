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

    public static Observable<ResultBean<List<MeiZhiBean>>> requestMeiZhiData(String category, int num, int page) {
        return RxRetrofitClient.getInstance()
                .RxRetrofitClient(HomeConstantUtils.REQUEST_URL)
                .create(IApiHomeServer.class)
                .getMeiZhiList(category, num, page);
    }

    public static Observable<WeatherResultBean<List<NowHeWeather5Bean>>> requestNowWeatherData(String category,
                                                                                               String city, String key) {
        return RxRetrofitClient.getInstance()
                .RxRetrofitClient(HomeConstantUtils.WEATHER_URL)
                .create(IApiHomeServer.class)
                .getNowWeather(category, city, key);
    }
}
