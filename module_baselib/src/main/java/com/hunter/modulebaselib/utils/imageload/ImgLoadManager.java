package com.hunter.modulebaselib.utils.imageload;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by HeQuanli on 2017/7/13.
 * 类说明：
 */

public class ImgLoadManager {

    private View mView;
    private String mImgUrl;
    private int mImgId;
    private int type;

    private ImageLoaderOptions mOptions;

    private static ImgLoadManager instance = null;

    private ImgLoadManager() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static ImgLoadManager getInstance() {
        if (instance == null) {
            synchronized (ImgLoadManager.class) {
                if (instance == null) {
                    instance = new ImgLoadManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param view
     * @param url
     * @returnv
     */
    public ImgLoadManager setViewAndUrl(@NonNull View view, @NonNull String url) {
        this.mView = view;
        this.mImgUrl = url;
        return this;
    }

    /**
     * @param view
     * @param imgId
     * @return
     */
    public ImgLoadManager setViewAndUrl(@NonNull View view, @NonNull int imgId) {
        this.mView = view;
        this.mImgId = imgId;
        return this;
    }

    public ImgLoadManager addOptions(ImageLoaderOptions options) {
        this.mOptions = options;
        return this;
    }

    public void build() {

    }
}
