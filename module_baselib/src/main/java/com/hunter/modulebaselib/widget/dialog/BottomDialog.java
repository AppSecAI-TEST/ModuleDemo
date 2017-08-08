package com.hunter.modulebaselib.widget.dialog;

import android.view.View;

import com.hunter.modulebaselib.R;

/**
 * 底部dilog
 * Created by Hunter on 2017/8/7.
 */

public class BottomDialog extends BaseDialogFragment {

    @Override
    protected boolean isBottom() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_dialog_bottom;
    }

    @Override
    protected void initViewAndEvent(View view) {

    }
}
