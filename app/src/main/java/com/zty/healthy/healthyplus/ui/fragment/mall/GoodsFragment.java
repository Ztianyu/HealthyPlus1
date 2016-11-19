package com.zty.healthy.healthyplus.ui.fragment.mall;

import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;

/**
 * 商品列表
 * Created by zty on 2016/10/12.
 */

public class GoodsFragment extends BaseFragment {
    private String id;

    public GoodsFragment(String id) {
        this.id = id;
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
