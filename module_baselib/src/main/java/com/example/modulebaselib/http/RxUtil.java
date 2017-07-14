package com.example.modulebaselib.http;


import com.example.modulebaselib.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HeQuanLi on 2017年5月20日.
 */
public class RxUtil
{
    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper()
    {    //compose简化线程
        return new ObservableTransformer<T, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ResultBean<T>, T> handleResult()
    {   //compose判断结果
        return new ObservableTransformer<ResultBean<T>, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
                return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ResultBean<T> tResultBean) throws Exception {

                        if (!tResultBean.isError())
                        {
                            return createData(tResultBean.getResults());
                        } else
                        {
                            return Observable.error(new RequestException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t)
    {
        return Observable.create(new ObservableOnSubscribe<T>()
        {
            @Override
            public void subscribe(ObservableEmitter<T> eEmitter) throws Exception {
                try
                {
                    eEmitter.onNext(t);
                    eEmitter.onComplete();
                } catch (Exception e)
                {
                    eEmitter.onError(e);
                }
            }
        });
    }
}
