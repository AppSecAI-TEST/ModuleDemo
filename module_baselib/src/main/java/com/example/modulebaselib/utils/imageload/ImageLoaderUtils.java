package com.example.modulebaselib.utils.imageload;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 对图片加载进行简单的封装
 * Created by HeQuanLi on 2017/5/20.
 */

public class ImageLoaderUtils {

    public static void dispalyImage( String path, ImageView view) {
        Glide.with(view.getContext()).load(path).into(view);
    }

    public static void dispalyImage(int path, ImageView view) {
        Glide.with(view.getContext()).load(path).into(view);
    }
}
