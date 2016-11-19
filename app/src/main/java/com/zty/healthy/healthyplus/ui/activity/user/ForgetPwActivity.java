package com.zty.healthy.healthyplus.ui.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.data.Keys;
import com.zty.healthy.healthyplus.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * 忘记密码、注册
 * Created by zty on 2016/10/13.
 */

public class ForgetPwActivity extends BaseActivity implements View.OnClickListener {

    private EditText editForgetPhone;
    private EditText editForgetCode;
    private Button btnForgetGetCode;
    private Button btnForgetStep1;

    private boolean isRegister = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_pw;
    }

    @Override
    protected void initView() {

        isRegister = getIntent().getBooleanExtra("isRegister", false);

        title.setText("手验验证");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        editForgetPhone = (EditText) findViewById(R.id.editForgetPhone);
        editForgetCode = (EditText) findViewById(R.id.editForgetCode);
        btnForgetGetCode = (Button) findViewById(R.id.btnForgetGetCode);
        btnForgetGetCode.setOnClickListener(this);
        btnForgetStep1 = (Button) findViewById(R.id.btnForgetStep1);
        btnForgetStep1.setOnClickListener(this);


    }

    private void initSMSSDK() {
        SMSSDK.initSDK(this, Keys.MobAppKey, Keys.MobAppSecret);

        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    startActivity(new Intent(ForgetPwActivity.this, SetNewActivity.class).putExtra("phone", phone).putExtra("isRegister", isRegister));

                } else if (result == SMSSDK.RESULT_ERROR) {
                    try {
                        Throwable throwable = (Throwable) data;
                        throwable.printStackTrace();
                        JSONObject object = new JSONObject(throwable.getMessage());
                        String des = object.optString("detail");//错误描述
                        int status = object.optInt("status");//错误代码
                        if (status > 0 && !TextUtils.isEmpty(des)) {
                            ToastUtils.showLong(ForgetPwActivity.this, des);
                            return;
                        }
                    } catch (Exception e) {
                        //do something
                    }
                }
            }
        });
        registerPage.show(this);

        finish();
    }

    @Override
    protected void initData() {
        initSMSSDK();
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
            case R.id.btnForgetGetCode:
                break;
            case R.id.btnForgetStep1:
                break;
        }
    }

}
