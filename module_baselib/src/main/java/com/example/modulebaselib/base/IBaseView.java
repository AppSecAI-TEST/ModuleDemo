package com.example.modulebaselib.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by HeQuanLi on 2016/12/9.
 */

public interface IBaseView {
    //显示错误信息
    void showErrorMsg(String errorMsg);
    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
