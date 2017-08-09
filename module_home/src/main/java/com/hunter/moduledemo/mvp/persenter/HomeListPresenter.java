package com.hunter.moduledemo.mvp.persenter;

import android.util.Log;

import com.hunter.modulebaselib.base.RxPresenter;
import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.modulebaselib.http.RxUtil;
import com.hunter.moduledemo.business.ApiHomeServerImpl;
import com.hunter.moduledemo.business.RxHome;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.bean.NowHeWeather5Bean;
import com.hunter.moduledemo.mvp.bean.WeatherResultBean;
import com.hunter.moduledemo.mvp.constract.HomeListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public class HomeListPresenter extends RxPresenter<HomeListContract.View>
        implements HomeListContract.Presenter {

    private int page = 1;

    @Inject
    public HomeListPresenter() {
    }

    @Override
    public void getMeiZhiData(String category, final boolean refresh) {
        if (refresh) {

            page = 1;
        } else {
            ++page;
        }
        ApiHomeServerImpl.requestMeiZhiData(category, 50, page)
                .compose(mView.<ResultBean<List<MeiZhiBean>>>bindToLife())
                .compose(RxUtil.<ResultBean<List<MeiZhiBean>>>rxSchedulerHelper())
                .compose(RxHome.<List<MeiZhiBean>>handleMeizhiResult())
                .subscribe(new Consumer<List<MeiZhiBean>>() {
                    @Override
                    public void accept(List<MeiZhiBean> studentBeen) throws Exception {
                        mView.displayData(studentBeen, refresh);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.d("HQL","HomeListPresenter->getMeiZhiData()"+throwable.getMessage());
                    }
                });
    }

    @Override
    public void getNowWeather(String now, String city, String key) {
        ApiHomeServerImpl.requestNowWeatherData(now, city, key)
                .compose(mView.<WeatherResultBean<List<NowHeWeather5Bean>>>bindToLife())
                .compose(RxUtil.<WeatherResultBean<List<NowHeWeather5Bean>>>rxSchedulerHelper())
                .compose(RxHome.<List<NowHeWeather5Bean>>handleWeatherResult())
                .subscribe(new Consumer<List<NowHeWeather5Bean>>() {
                    @Override
                    public void accept(List<NowHeWeather5Bean> beans) throws Exception {
                        mView.showNowWeather(beans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("HQL", throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }
}
