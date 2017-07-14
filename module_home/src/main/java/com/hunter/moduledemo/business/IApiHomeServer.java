package com.hunter.moduledemo.business;

import com.example.modulebaselib.bean.ResultBean;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：
 */

public interface IApiHomeServer {
    /**
     * 主页
     */
    @GET("data/{category}/{num}/{page}")
    Observable<ResultBean<List<MeiZhiBean>>> getMeiZhiList(@Path("category") String category,
                                                         @Path("num") int num, @Path("page") int page);

}
