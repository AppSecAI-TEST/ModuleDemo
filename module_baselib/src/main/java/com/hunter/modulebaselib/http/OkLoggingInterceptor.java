package com.hunter.modulebaselib.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：自定义OKhttp3日志输出
 */

public class OkLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
