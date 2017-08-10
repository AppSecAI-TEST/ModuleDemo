package com.hunter.modulebaselib.http.gson;

import com.google.gson.Gson;
import com.hunter.modulebaselib.bean.ResultBean;
import com.hunter.modulebaselib.http.exception.ServerException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/8/10.
 */

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            //ResultResponse 只解析result字段
            ResultBean resultResponse = gson.fromJson(response, ResultBean.class);
            if (resultResponse.getResults() != null) {
                //result==0表示成功返回，继续用本来的Model类解析
                return gson.fromJson(response, type);
            } else {
                //这儿可以传入错误信息和状态码
                throw new ServerException("数据请求失败，请重试！");
            }
        } finally {
        }
    }
}
