package com.zty.healthy.healthyplus.ui.activity.user;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.service.UserService;
import com.zty.healthy.healthyplus.utils.ToastUtils;

/**
 * 修改密码
 * Created by zty on 2016/9/1.
 */
public class ResetPassWordActivity extends BaseActivity implements View.OnClickListener {

    private EditText editOldPw, editResetNewPw, editResetNewSurePw;
    private ImageView imgResetPassWordShow1, imgResetPassWordShow2;
    private Button btnResetPw;

    private boolean isShow1 = false, isShow2 = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_pw;
    }

    @Override
    protected void initView() {
        title.setText("修改密码");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        editOldPw = (EditText) findViewById(R.id.editOldPw);
        editResetNewPw = (EditText) findViewById(R.id.editResetNewPw);
        editResetNewSurePw = (EditText) findViewById(R.id.editResetNewSurePw);

        imgResetPassWordShow1 = (ImageView) findViewById(R.id.imgResetPassWordShow1);
        imgResetPassWordShow1.setOnClickListener(this);
        imgResetPassWordShow2 = (ImageView) findViewById(R.id.imgResetPassWordShow2);
        imgResetPassWordShow2.setOnClickListener(this);

        btnResetPw = (Button) findViewById(R.id.btnResetPw);
        btnResetPw.setOnClickListener(this);

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
            case R.id.imgResetPassWordShow1:
                isShow1 = !isShow1;
                if (isShow1) {
                    editResetNewPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editResetNewPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.imgResetPassWordShow2:
                isShow2 = !isShow2;
                if (isShow2) {
                    editResetNewSurePw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editResetNewSurePw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.btnResetPw:
                if (checkData())
                    UserService.resetNewPw(this, editOldPw.getText().toString(),
                            editResetNewPw.getText().toString(), this);
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(editOldPw.getText().toString())) {
            ToastUtils.show(this, "请输入原密码");
            return false;
        }
        if (TextUtils.isEmpty(editResetNewPw.getText().toString())) {
            ToastUtils.show(this, "请输入新密码");
            return false;
        }
        if (TextUtils.isEmpty(editResetNewSurePw.getText().toString())) {
            ToastUtils.show(this, "请确认密码");
            return false;
        }
        if (editResetNewPw.getText().toString().equals(editResetNewSurePw.getText().toString())) {
            ToastUtils.show(this, "两次密码不一样，请重新输入");
            return false;
        }
        return true;
    }
}
