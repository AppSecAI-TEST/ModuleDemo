package com.hunter.moduledemo.mvp.bean;

/**
 * Created by Hunter on 2017/7/22.
 * 天气返回结果
 */

public class WeatherResultBean<T> {

    private T HeWeather5;

    public T getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(T HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }
}
