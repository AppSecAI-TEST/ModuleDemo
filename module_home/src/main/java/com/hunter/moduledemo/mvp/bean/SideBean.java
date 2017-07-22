package com.hunter.moduledemo.mvp.bean;

/**
 * Created by Hunter on 2017/7/22.
 * 侧滑菜单实体类
 */

public class SideBean {

    public SideBean() {
    }

    public SideBean(String mSideName, int mSideIcon) {
        this.mSideName = mSideName;
        this.mSideIcon = mSideIcon;
    }

    private String mSideName;
    private int mSideIcon;

    public String getmSideName() {
        return mSideName;
    }

    public void setmSideName(String mSideName) {
        this.mSideName = mSideName;
    }

    public int getmSideIcon() {
        return mSideIcon;
    }

    public void setmSideIcon(int mSideIcon) {
        this.mSideIcon = mSideIcon;
    }
}
