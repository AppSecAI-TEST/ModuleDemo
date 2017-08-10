package com.hunter.modulebaselib.http.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Created by HeQuanli on 2017/7/17.
 * 类说明：异常处理的简单封装
 */

public class RxException<T extends Throwable> implements Consumer<T> {

    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "主机连接错误";

    private Consumer<? super Throwable> onError;

    public RxException(Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    @Override
    public void accept(T t) throws Exception {
        if (t instanceof SocketTimeoutException) {
            onError.accept(new Throwable(SOCKETTIMEOUTEXCEPTION));
        } else if (t instanceof ConnectException) {
            onError.accept(new Throwable(CONNECTEXCEPTION));
        } else if (t instanceof UnknownHostException) {
            onError.accept(new Throwable(UNKNOWNHOSTEXCEPTION));
        } else if (t instanceof HttpException) {
            onError.accept(new Throwable(CONNECTEXCEPTION));
        } else {
            onError.accept(t);
        }
    }
}

