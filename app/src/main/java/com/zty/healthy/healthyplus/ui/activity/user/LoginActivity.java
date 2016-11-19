package com.zty.healthy.healthyplus.ui.activity.user;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.manager.AppManager;
import com.zty.healthy.healthyplus.model.LoginModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.UserService;
import com.zty.healthy.healthyplus.ui.activity.MainActivity;
import com.zty.healthy.healthyplus.utils.JsonUtils;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;
import com.zty.healthy.healthyplus.utils.ToastUtils;

/**
 * 登录界面
 * Created by zty on 2016/8/22.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText phone;
    private EditText passWord;
    private ImageView imgPassWordShow;
    private Button btnLogin;
    private TextView textForgetPw, textRegister;

    private boolean isShow = false;//密码可见不可见
    private boolean isRegister = false;//是否是注册

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        title.setText("登 录");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        phone = (EditText) findViewById(R.id.editUserName);
        passWord = (EditText) findViewById(R.id.editPassWord);
        imgPassWordShow = (ImageView) findViewById(R.id.imgPassWordShow);
        imgPassWordShow.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        textForgetPw = (TextView) findViewById(R.id.textForgetPw);
        textForgetPw.setOnClickListener(this);
        textRegister = (TextView) findViewById(R.id.textRegister);
        textRegister.setOnClickListener(this);

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
            LoginModel model = JsonUtils.changeJsonToBean(resultBean.getResult(), LoginModel.class);
            toLogin(model);
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

    }

    private void toLogin(LoginModel model) {
        SharedPrefUtils.setString(this, SharedPrefUtils.USER_ID, model.getUserId());
        SharedPrefUtils.setString(this, SharedPrefUtils.TAKEN_ID, model.getTokenId());
        SharedPrefUtils.setString(this, SharedPrefUtils.FAMILY_NUM, model.getNumber());

        AppManager.getInstance().finishAllActivity();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.imgPassWordShow:
                isShow = !isShow;
                if (isShow) {
                    passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    passWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.textRegister:
                isRegister = true;
            case R.id.textForgetPw:
                isRegister = false;
                startActivity(new Intent(this, ForgetPwActivity.class).putExtra("isRegister", isRegister));
                break;
            case R.id.btnLogin:
                if (checkInput())
                    UserService.login(phone.getText().toString(), passWord.getText().toString(), this);
                break;
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(phone.getText().toString())) {
            ToastUtils.show(this, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(passWord.getText().toString())) {
            ToastUtils.show(this, "请输入密码");
            return false;
        }
        return true;
    }
}
