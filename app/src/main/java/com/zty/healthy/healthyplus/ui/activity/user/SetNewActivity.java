package com.zty.healthy.healthyplus.ui.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.manager.AppManager;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.UserService;
import com.zty.healthy.healthyplus.ui.activity.MainActivity;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.ToastUtils;

/**
 * 设置新密码
 * Created by zty on 2016/10/13.
 */

public class SetNewActivity extends BaseActivity implements View.OnClickListener {

    private EditText editNewPw;
    private EditText editSurePw;
    private Button btnForgetStep2;

    private String phone;
    private boolean isRegister;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_new_pw;
    }

    @Override
    protected void initView() {

        phone = getIntent().getStringExtra("phone");
        isRegister = getIntent().getBooleanExtra("isRegister", false);

        title.setText("设置密码");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        editNewPw = (EditText) findViewById(R.id.editNewPw);
        editSurePw = (EditText) findViewById(R.id.editSurePw);
        btnForgetStep2 = (Button) findViewById(R.id.btnForgetStep2);
        btnForgetStep2.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response, false);
        if (resultBean.isSuccess()) {
            toLogin();
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.btnForgetStep2:
                if (checkData()) {
                    if (isRegister) {
                        UserService.setPassWord(phone, editNewPw.getText().toString(), this);
                    } else {
                        UserService.register(phone, editNewPw.getText().toString(), this);
                    }
                }

                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(editNewPw.getText().toString())) {
            ToastUtils.show(this, "请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(editSurePw.getText().toString())) {
            ToastUtils.show(this, "请确认密码");
            return false;
        }
        if (!editNewPw.getText().toString().equals(editSurePw.getText().toString())) {
            ToastUtils.show(this, "两次输入密码不一样，请重新输入");
            return false;
        }
        return true;
    }

    private void toLogin() {
        AppManager.getInstance().finishAllActivity();
        startActivity(new Intent(this, MainActivity.class));
    }
}
