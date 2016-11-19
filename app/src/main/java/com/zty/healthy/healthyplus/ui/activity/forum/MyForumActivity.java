package com.zty.healthy.healthyplus.ui.activity.forum;

import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

/**
 * 我的帖子列表
 * Created by zty on 2016/10/12.
 */

public class MyForumActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getContentView() {
        return R.layout.view_list;
    }

    @Override
    protected void initView() {

        title.setText("我的帖子");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");
        right.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                finish();
                break;
        }

    }
}
