package com.zty.healthy.healthyplus.ui.activity;

import android.content.Intent;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

/**
 * 启动页
 * Created by zty on 2016/8/16.
 */
public class LoadingActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
        return R.layout.activity_loading;
    }

    @Override
    protected void initView() {

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
}
