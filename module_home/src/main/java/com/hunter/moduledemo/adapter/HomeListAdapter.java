package com.hunter.moduledemo.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hunter.moduledemo.R;
import com.hunter.moduledemo.mvp.bean.MeiZhiBean;

/**
 * Created by HeQuanli on 2017/7/14.
 * 类说明：主页列表数据适配器
 */

public class HomeListAdapter extends BaseQuickAdapter<MeiZhiBean, BaseViewHolder> {

    public HomeListAdapter() {
        super(R.layout.item_list_home);
    }

    @Override
    protected void convert(BaseViewHolder holder, MeiZhiBean item) {
        //ImageLoaderUtils.dispalyImage(item.getUrl(), (ImageView) holder.getView(R.id.iv_pic));
        Glide.with(holder.getView(R.id.iv_pic).getContext()).load(item.getUrl()).into((ImageView)holder.getView(R.id.iv_pic));
        holder.setText(R.id.tv_date, item.getPublishedAt());
    }
}
