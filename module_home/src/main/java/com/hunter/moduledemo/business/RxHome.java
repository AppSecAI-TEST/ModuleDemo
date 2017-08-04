package com.hunter.moduledemo.business;

import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.modulebaselib.http.RequestException;
import com.hunter.modulebaselib.http.RxUtil;
import com.hunter.moduledemo.mvp.bean.WeatherResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public class RxHome {
    /**
     * 统一返回妹纸结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ResultBean<T>, T> handleMeizhiResult() {   //compose判断结果
        return new ObservableTransformer<ResultBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
                return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ResultBean<T> tResultBean) throws Exception {


                        if (tResultBean != null) {
                            return RxUtil.createData(tResultBean.getResults());
                        } else {
                            return Observable.error(new RequestException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回妹纸结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<WeatherResultBean<T>, T> handleWeatherResult() {   //compose判断结果
        return new ObservableTransformer<WeatherResultBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<WeatherResultBean<T>> upstream) {
                return upstream.flatMap(new Function<WeatherResultBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(WeatherResultBean<T> tResultBean) throws Exception {


                        if (tResultBean != null) {
                            return RxUtil.createData(tResultBean.getHeWeather5());
                        } else {
                            return Observable.error(new RequestException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }
}
