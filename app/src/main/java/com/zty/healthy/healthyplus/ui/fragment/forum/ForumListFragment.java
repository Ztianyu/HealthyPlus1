package com.zty.healthy.healthyplus.ui.fragment.forum;

import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;

/**
 * 论坛列表
 * Created by zty on 2016/10/12.
 */

public class ForumListFragment extends BaseFragment {

    private int page;// 0：社区交流；1：健康知识；2：视频讲座

    public ForumListFragment(int page) {
        this.page = page;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_list;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
