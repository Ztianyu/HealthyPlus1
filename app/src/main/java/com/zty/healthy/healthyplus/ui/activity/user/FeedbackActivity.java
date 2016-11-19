package com.zty.healthy.healthyplus.ui.activity.user;

import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

/**
 * 意见反馈
 * Created by zty on 2016/9/1.
 */
public class FeedbackActivity extends BaseActivity  implements View.OnClickListener{
    @Override
    protected int getContentView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        title.setText("意见反馈");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
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
