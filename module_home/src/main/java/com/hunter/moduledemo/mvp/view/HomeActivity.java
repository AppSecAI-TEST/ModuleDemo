package com.hunter.moduledemo.mvp.view;

import android.support.v7.widget.RecyclerView;

import com.example.modulebaselib.base.BaseActivity;
import com.example.modulebaselib.base.BaseApplication;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.dagger2.component.DaggerHomeActivityComponent;
import com.hunter.moduledemo.dagger2.module.HomeActivityModule;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.constract.HomeListContract;
import com.hunter.moduledemo.mvp.persenter.HomeListPresenter;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeListPresenter> implements HomeListContract.View {

    @BindView(R.id.rv_main_list)
    RecyclerView mRvMainList;

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void displayData(List<MeiZhiBean> list) {

    }

    @Override
    protected void initInject() {
        DaggerHomeActivityComponent.builder()
                .applicationComponent(BaseApplication.getAppComponent())
                .homeActivityModule(new HomeActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewAndEvent() {
        mPresenter.getMeiZhiData("福利", this);
    }
}
