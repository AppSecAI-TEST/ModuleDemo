package com.hunter.moduledemo.business;

import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.bean.NowHeWeather5Bean;
import com.hunter.moduledemo.mvp.bean.WeatherResultBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public interface IApiHomeServer  {
    /**
     * 主页
     */
    @GET("data/{category}/{num}/{page}")
    Observable<ResultBean<List<MeiZhiBean>>> getMeiZhiList(@Path("category") String category,
                                                           @Path("num") int num,
                                                           @Path("page") int page);

    //https://free-api.heweather.com/v5/now?city=yourcity&key=yourkey
    @GET("v5/{now}")
    Observable<WeatherResultBean<List<NowHeWeather5Bean>>> getNowWeather(@Path("now") String now,
                                                                         @Query("city") String city,
                                                                         @Query("key") String key);
}
