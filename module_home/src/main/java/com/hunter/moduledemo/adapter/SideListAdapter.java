package com.hunter.moduledemo.adapter;


import android.content.res.Resources;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.mvp.bean.SideBean;


/**
 * Created by Hunter on 2017/7/22.
 */

public class SideListAdapter extends BaseQuickAdapter<SideBean, BaseViewHolder> {
    public SideListAdapter() {
        super(R.layout.item_list_side);
    }

    @Override
    protected void convert(BaseViewHolder holder, SideBean item) {
        initMenu(holder, item);
    }

    private void initMenu(BaseViewHolder holder, SideBean item) {
        int pos = holder.getLayoutPosition();
        Resources resources = holder.getView(R.id.ll_sidebg).getContext().getResources();
        holder.setText(R.id.tv_sidename, item.getmSideName());
        holder.setBackgroundRes(R.id.iv_sideicon, item.getmSideIcon());

        switch (pos) {
            case 1:
                holder.setBackgroundRes(R.id.ll_sidebg, R.drawable.side_menu_percenter);
                holder.setTextColor(R.id.tv_sidename, resources.getColor(R.color.color_D4237A));
                break;
            case 2:
                holder.setBackgroundRes(R.id.ll_sidebg, R.drawable.side_menu_weather);
                holder.setTextColor(R.id.tv_sidename, resources.getColor(R.color.color_1AFA29));
                break;
            case 3:
                holder.setBackgroundRes(R.id.ll_sidebg, R.drawable.side_menu_about);
                holder.setTextColor(R.id.tv_sidename, resources.getColor(R.color.color_1AFA29));
                break;
            case 4:
                holder.setBackgroundRes(R.id.ll_sidebg, R.drawable.side_menu_more);
                holder.setTextColor(R.id.tv_sidename, resources.getColor(R.color.color_1AFA29));
                break;
            case 5:
                holder.setBackgroundRes(R.id.ll_sidebg, R.drawable.side_menu_other);
                holder.setTextColor(R.id.tv_sidename, resources.getColor(R.color.color_1AFA29));
                break;
        }
    }
}
