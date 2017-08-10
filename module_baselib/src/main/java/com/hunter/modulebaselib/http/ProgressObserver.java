package com.hunter.modulebaselib.http;


import android.app.ProgressDialog;

import com.hunter.modulebaselib.base.BaseApplication;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/10.
 */

public abstract class ProgressObserver<T> implements Observer<T> {
    private ProgressDialog progressDialog = null;

    @Override
    public void onSubscribe(Disposable d) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseApplication.getInstance());
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        next(t);
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage());
    }

    @Override
    public void onComplete() {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    protected abstract void next(T t);

    protected abstract void error(String msg);
}
