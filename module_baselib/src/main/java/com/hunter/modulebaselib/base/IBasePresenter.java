package com.hunter.modulebaselib.base;

/**
 * Created by HeQuanLi on 2016/12/9.
 */

public interface IBasePresenter<T extends IBaseView> {

    void attachView(T view);

    void detachView();
}
