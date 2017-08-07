package com.hunter.moduledemo.mvp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.hunter.modulebaselib.base.BaseActivity;
import com.hunter.modulebaselib.base.BaseApplication;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.adapter.HomeListAdapter;
import com.hunter.moduledemo.adapter.SideListAdapter;
import com.hunter.moduledemo.dagger2.component.DaggerHomeActivityComponent;
import com.hunter.moduledemo.dagger2.module.HomeActivityModule;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;
import com.hunter.moduledemo.mvp.bean.NowHeWeather5Bean;
import com.hunter.moduledemo.mvp.bean.SideBean;
import com.hunter.moduledemo.mvp.constract.HomeListContract;
import com.hunter.moduledemo.mvp.persenter.HomeListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeListPresenter>
        implements HomeListContract.View, BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, DrawerLayout.DrawerListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_main_list)
    RecyclerView mRvMainList;
    @BindView(R.id.home_swiperefresh_layout)
    SwipeRefreshLayout mSwiperefreshLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.side_menu)
    RecyclerView sideMenu;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_temp)
    TextView mTvTemp;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private HomeListAdapter mAdapter;
    private RecyclerView mRvMenuList;
    private SideListAdapter mSideAdapter;
    private boolean firstOpen = true;

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

        mToolbar.setTitle("妹纸");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mRvMenuList = (RecyclerView) findViewById(R.id.side_menu);


        mPresenter.getMeiZhiData("福利", true);
        mPresenter.getNowWeather("now", "成都", "49c27061632b4ad6a6725b7bc9152dd3");

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
        mAdapter.setDuration(500);
        mRvMainList.setAdapter(mAdapter);
        mDrawerLayout.addDrawerListener(this);
        mAdapter.setOnItemClickListener(this);


        mSideAdapter = new SideListAdapter();
        LinearLayoutManager mSideLayout = new LinearLayoutManager(this);
        mSideLayout.setOrientation(LinearLayoutManager.VERTICAL);
        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        mSideAdapter.addHeaderView(header);
        mSideAdapter.openLoadAnimation(new SlideInRightAnimation());
        mRvMenuList.setLayoutManager(mSideLayout);
        mRvMenuList.setAdapter(mSideAdapter);
    }

    @Override
    public void showNowWeather(List<NowHeWeather5Bean> list) {
        mTvCity.setText(list.get(0).getBasic().getCnty() + "·" + list.get(0).getBasic().getCity());
        mTvTemp.setText("Temp：" + list.get(0).getNow().getTmp() + " ℃");
        mTvState.setText("State：" + list.get(0).getNow().getCond().getTxt());
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

    private List<SideBean> getMenu() {
        List<SideBean> menu = new ArrayList<>();
        SideBean percenter = new SideBean("个人中心", R.drawable.ic_side_personalcenter);
        menu.add(percenter);
        SideBean weather = new SideBean("最近天气", R.drawable.ic_side_weather);
        menu.add(weather);
        menu.add(weather);
        menu.add(weather);
        menu.add(weather);
        return menu;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        if (firstOpen) {
            mSideAdapter.setNewData(getMenu());
        }
        firstOpen = false;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MeiZhiBean data = (MeiZhiBean) adapter.getData().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        ARouter.getInstance().build("/home/seepicture")
                .with(bundle)
                .navigation();
    }
}
