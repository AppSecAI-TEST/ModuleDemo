package com.hunter.moduledemo.business;

import com.example.modulebaselib.bean.ResultBean;
import com.example.modulebaselib.http.RxRetrofit;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
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

    private static IApiHomeServer mIApiServer = new Retrofit.Builder()
            .baseUrl(HomeConstantUtils.REQUEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(RxRetrofit.getInstance())
            .build()
            .create(IApiHomeServer.class);

    public static Observable<ResultBean<List<MeiZhiBean>>> requestMeiZhiData(String category, int num, int page) {
        return mIApiServer.getMeiZhiList(category, num, page);
    }
}
