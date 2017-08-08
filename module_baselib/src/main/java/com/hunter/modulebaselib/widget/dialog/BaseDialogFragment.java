package com.hunter.modulebaselib.widget.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hunter.modulebaselib.R;

/**
 * Created by HeQuanli on 2017/7/20.
 * 类说明：dialog基类
 */

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        boolean bottom = isBottom();
        if (bottom) {
            // 设置宽度为屏宽, 靠近屏幕底部。
            setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Black_NoTitleBar);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM; // 紧贴底部
        }

        View view = inflater.inflate(getLayout(),
                ((ViewGroup) window.findViewById(android.R.id.content)), false);
        initViewAndEvent(view);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (bottom) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
        }
        getDialog().getWindow().getAttributes().windowAnimations = R.style.BottomDialogAnimation;
        return view;
    }

    /** Dialog显示位置 */
    protected abstract boolean isBottom();

    /** 获取布局视图id */
    protected abstract int getLayout();

    /** 初始化View和事件 */
    protected abstract void initViewAndEvent(View view);
}
