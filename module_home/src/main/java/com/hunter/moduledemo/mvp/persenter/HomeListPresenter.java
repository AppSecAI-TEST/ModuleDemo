package com.hunter.moduledemo.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.example.modulebaselib.base.RxPresenter;
import com.example.modulebaselib.bean.ResultBean;
import com.example.modulebaselib.http.RxUtil;
import com.hunter.moduledemo.business.ApiHomeServerImpl;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
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

    @Inject
    public HomeListPresenter() {
    }

    @Override
    public void getMeiZhiData(String category, Context context) {
        ApiHomeServerImpl.requestMeiZhiData(category, 10, 1)
                .compose(mView.<ResultBean<List<MeiZhiBean>>>bindToLife())
                .compose(RxUtil.<ResultBean<List<MeiZhiBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<MeiZhiBean>>handleResult())
                .subscribe(new Consumer<List<MeiZhiBean>>() {
                    @Override
                    public void accept(List<MeiZhiBean> studentBeen) throws Exception {
                        mView.displayData(studentBeen);
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
