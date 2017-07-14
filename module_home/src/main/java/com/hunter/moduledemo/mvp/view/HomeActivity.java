package com.hunter.moduledemo.mvp.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.modulebaselib.base.BaseActivity;
import com.example.modulebaselib.base.BaseApplication;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.adapter.HomeListAdapter;
import com.hunter.moduledemo.dagger2.component.DaggerHomeActivityComponent;
import com.hunter.moduledemo.dagger2.module.HomeActivityModule;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.constract.HomeListContract;
import com.hunter.moduledemo.mvp.persenter.HomeListPresenter;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeListPresenter>
        implements HomeListContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_main_list)
    RecyclerView mRvMainList;

    private HomeListAdapter mAdapter;

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

        mAdapter = new HomeListAdapter();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRvMainList);

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        mRvMainList.setLayoutManager(layout);
        mRvMainList.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void displayData(List<MeiZhiBean> list) {
        mAdapter.addData(list);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
