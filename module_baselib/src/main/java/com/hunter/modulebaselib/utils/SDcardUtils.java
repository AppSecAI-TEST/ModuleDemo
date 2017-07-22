package com.hunter.modulebaselib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 获取缓存路径
 * Created by HeQuanLi on 2017/7/14.
 */

public class SDcardUtils {
    public static File getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + "Hunter");
    }
}
