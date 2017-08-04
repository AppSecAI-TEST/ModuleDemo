package com.hunter.moduledemo.mvp.constract;

import com.hunter.modulebaselib.base.IBasePresenter;
import com.hunter.modulebaselib.base.IBaseView;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.bean.NowHeWeather5Bean;

import java.util.List;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：主页列表契约
 */

public interface HomeListContract {
    interface View extends IBaseView {

        void displayData(List<MeiZhiBean> list, boolean refresh);

        void showNowWeather(List<NowHeWeather5Bean> list);
    }

    interface Presenter extends IBasePresenter<View> {

        void getMeiZhiData(String category, boolean refresh);

        void getNowWeather(String city, String now, String key);
    }
}