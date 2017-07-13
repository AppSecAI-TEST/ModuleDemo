package com.example.modulebaselib.utils.imageload;

import android.view.View;

/**
 * Created by HeQuanli on 2017/7/13.
 */

public interface IImgLoad {
    /**
     * 显示图片
     *
     * @param view    用于要展示的视图
     * @param imgUrl  显示地址
     */
    void shouImage(View view, String imgUrl);

    /**
     * 显示图片
     *
     * @param view    用于要展示的视图
     * @param imgId   要显示的图片ID
     */
    void shouImage(View view, int imgId);

//    /**
//     * 显示图片
//     *
//     * @param view    用于要展示的视图
//     * @param imgUrl  显示地址
//     * @param options 图片显示配置
//     */
//    void shouImage(View view, String imgUrl, ImageLoaderOptions options);
//
//    /**
//     * 显示图片
//     *
//     * @param view    用于要展示的视图
//     * @param imgId   要显示的图片ID
//     * @param options 图片显示配置
//     */
//    void shouImage(View view, int imgId, ImageLoaderOptions options);
}
