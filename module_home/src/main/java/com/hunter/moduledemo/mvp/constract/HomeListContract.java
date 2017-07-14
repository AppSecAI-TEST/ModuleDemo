package com.hunter.moduledemo.mvp.constract;

import com.example.modulebaselib.base.IBasePresenter;
import com.example.modulebaselib.base.IBaseView;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;

import java.util.List;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：主页列表契约契约
 */

public interface HomeListContract {
    interface View extends IBaseView {
        void displayData(List<MeiZhiBean> list, boolean refresh);
    }

    interface Presenter extends IBasePresenter<View> {
        void getMeiZhiData(String category, boolean refresh);
    }
}