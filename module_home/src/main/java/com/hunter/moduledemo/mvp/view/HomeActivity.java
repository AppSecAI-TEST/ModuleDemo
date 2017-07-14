package com.hunter.moduledemo.mvp.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
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
        implements HomeListContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_main_list)
    RecyclerView mRvMainList;

    @BindView(R.id.home_swiperefresh_layout)
    SwipeRefreshLayout mSwiperefreshLayout;

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
        mPresenter.getMeiZhiData("福利", true);

//        SlideInUpAnimator animation =  new SlideInUpAnimator(new OvershootInterpolator(1f));
//        mRvMainList.setItemAnimator(animation);

        mSwiperefreshLayout.setOnRefreshListener(this);
        mSwiperefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mAdapter = new HomeListAdapter();
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRvMainList);

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        mRvMainList.setLayoutManager(layout);
        mAdapter.openLoadAnimation(new SlideInBottomAnimation());
        mRvMainList.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void displayData(List<MeiZhiBean> list, boolean refresh) {
        if (refresh) {
            mAdapter.setNewData(list);
            mAdapter.loadMoreComplete();
            mSwiperefreshLayout.setRefreshing(false);
        } else {
            if (list.size() <= 0 || list == null) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.addData(list);
                mAdapter.loadMoreComplete();
            }
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMeiZhiData("福利", false);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getMeiZhiData("福利", true);
    }
}
