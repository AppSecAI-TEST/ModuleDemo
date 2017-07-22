package com.hunter.modulebaselib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by HeQuanLi on 2016/12/14.
 * Activity基类
 */
public abstract class BaseActivity<T extends IBasePresenter>
        extends RxAppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;
    protected Activity mContext;
    //视图绑定
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        BaseApplication.getInstance().addActivity(this);
        initViewAndEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        //解除视图绑定
        mUnBinder.unbind();
        BaseApplication.getInstance().removeActivity(this);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    protected abstract void initInject();

    protected abstract int getLayout();

    protected abstract void initViewAndEvent();
}
