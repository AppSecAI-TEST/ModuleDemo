package com.hunter.moduledemo.business;

import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.modulebaselib.http.RxUtil;
import com.hunter.modulebaselib.http.exception.ServerException;
import com.hunter.moduledemo.mvp.bean.WeatherResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：Home的统一处理返回结果
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
                    public ObservableSource<T> apply(ResultBean<T> tResultBean) {
                        if (tResultBean != null) {
                            return RxUtil.createData(tResultBean.getResults());
                        } else {
                            //这儿可以传入错误信息和状态码
                            throw new ServerException("数据请求失败，请重试！");
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回天气结果处理
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
                            //这儿可以传入错误信息和状态码
                            throw new ServerException("数据请求失败，请重试！");
                        }
                    }
                });
            }
        };
    }
}
