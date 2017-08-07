package com.hunter.modulebaselib.widget.seepicture;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Hunter on 2017/8/6.
 */

public class SeePictureImageView extends AppCompatImageView {

    //开始Y坐标
    private int startY;
    //结束Y坐标
    private int endY;


    private ActionListener listener;

    public SeePictureImageView(Context context) {
        super(context);
    }

    public SeePictureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeePictureImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) event.getY();
                Log.d("HQL", "ACTION_DOWN" + startY);
                break;
            case MotionEvent.ACTION_MOVE:
                endY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("HQL", "ACTION_UP" + endY);
                int distance = startY - endY;
                if (distance > 100) {
                    Log.d("HQL", "距离：" + distance);
                    listener.ActionListener(false);
                } else {
                    listener.ActionListener(true);
                }
                break;
        }
        return true;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
}
