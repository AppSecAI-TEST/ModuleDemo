package com.hunter.moduledemo.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hunter.modulebaselib.utils.imageload.ImageLoaderUtils;
import com.hunter.modulebaselib.widget.dialog.BottomDialog;
import com.hunter.modulebaselib.widget.seepicture.ActionListener;
import com.hunter.modulebaselib.widget.seepicture.SeePictureImageView;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全屏查看大图
 * Created by Hunter on 2017/8/6.
 */

@Route(path = "/home/seepicture")
public class SeePictureActivity extends Activity implements ActionListener {
    @BindView(R.id.iv_bigpic)
    SeePictureImageView ivBigpic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seepicture);
        ButterKnife.bind(this);
        initViewAndEvent();
    }

    protected void initViewAndEvent() {
        Bundle data = getIntent().getExtras();
        MeiZhiBean meiZhiBean = (MeiZhiBean) data.get("data");
        if (meiZhiBean != null) {
            ImageLoaderUtils.dispalyImage(meiZhiBean.getUrl(), ivBigpic);
        }
        ivBigpic.setListener(this);
    }

    @Override
    public void ActionListener(boolean click) {
        if (click) {
            finish();
        } else {
            BottomDialog dialog = new BottomDialog();
            dialog.show(getFragmentManager(), "HQL");
        }
    }
}
