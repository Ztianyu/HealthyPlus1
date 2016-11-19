package com.zty.healthy.healthyplus.ui.activity.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.model.AdviceModel;

/**
 * 健康方案详情
 * Created by zty on 2016/11/5.
 */

public class SchemeDetailsActivity extends BaseActivity implements View.OnClickListener {

    private AdviceModel model;
    private String str = "的健康方案";
    private TextView textSchemeTitle, textSchemeTime, textSchemeContent, textSchemeDoctor;
    private ImageView imgSchemeWeiXin, imgSchemeVideoCall;

    public static String familyMemberName;

    @Override
    protected int getContentView() {
        return R.layout.details_scheme;
    }

    @Override
    protected void initView() {
        model = (AdviceModel) getIntent().getSerializableExtra("scheme");
        title.setText(familyMemberName + str);

        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        textSchemeTitle = (TextView) findViewById(R.id.textSchemeTitle);
        textSchemeTime = (TextView) findViewById(R.id.textSchemeTime);
        textSchemeContent = (TextView) findViewById(R.id.textSchemeContent);
        textSchemeDoctor = (TextView) findViewById(R.id.textSchemeDoctor);
        imgSchemeWeiXin = (ImageView) findViewById(R.id.imgSchemeWeiXin);
        imgSchemeWeiXin.setOnClickListener(this);
        imgSchemeVideoCall = (ImageView) findViewById(R.id.imgSchemeVideoCall);
        imgSchemeVideoCall.setOnClickListener(this);

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
            case R.id.imgSchemeWeiXin:
                break;
            case R.id.imgSchemeVideoCall:
                break;
        }

    }
}
