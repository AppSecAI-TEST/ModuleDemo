package com.example.modulebaselib.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by HeQuanLi on 2016/12/9.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends IBaseView> implements IBasePresenter<T>
{

    protected T mView;
    protected CompositeDisposable mCompositeSubscription;


    @Override
    public void attachView(T view)
    {
        this.mView = view;
    }

    @Override
    public void detachView()
    {
        this.mView = null;
        unSubscribe();
    }

    protected void unSubscribe()
    {
        if (mCompositeSubscription != null)
        {
            mCompositeSubscription.dispose();
        }
    }

    protected void addSubscrebe(Disposable disposable)
    {
        if (mCompositeSubscription == null)
        {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }
}

