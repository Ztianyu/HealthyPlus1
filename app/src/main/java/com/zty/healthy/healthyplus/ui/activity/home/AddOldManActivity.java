package com.zty.healthy.healthyplus.ui.activity.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.HealthyService;
import com.zty.healthy.healthyplus.service.RequestManager;
import com.zty.healthy.healthyplus.service.Urls;
import com.zty.healthy.healthyplus.utils.ImageUtils;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.SelectPicUtils;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;
import com.zty.healthy.healthyplus.utils.ToastUtils;
import com.zty.healthy.healthyplus.widget.SelectPicPopupWindow;

/**
 * 添加老人
 * Created by zty on 2016/8/27.
 */
public class AddOldManActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout main;

    private CircularImageView img;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;

    private EditText editOldManName;
    private RadioGroup radioGroupAdd;
    private EditText editHeight;
    private EditText editWeight;
    private EditText editAppellation;
    private EditText editBirth;


    private Button btnAddOldMan;

    private String sex;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_old;
    }

    @Override
    protected void initView() {
        title.setText("档案添加");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");

        img = (CircularImageView) findViewById(R.id.imgHealthyIcon);
        img.setOnClickListener(this);

        main = (LinearLayout) findViewById(R.id.layoutAddOldMan);

        editOldManName = (EditText) findViewById(R.id.editOldManName);
        radioGroupAdd = (RadioGroup) findViewById(R.id.radioGroupAdd);
        radioGroupAdd.setOnCheckedChangeListener(checkedChangeListener);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editAppellation = (EditText) findViewById(R.id.editAppellation);
        editBirth = (EditText) findViewById(R.id.editBirth);


        btnAddOldMan = (Button) findViewById(R.id.btnAddOldMan);
        btnAddOldMan.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        HealthyService.getFamilyDiseasesOption(this, 1, this);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

        ResultBean resultBean = ResultUtil.getResult(response, false);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case 1:
                    break;
                case 2:
                    break;
            }

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
            case R.id.imgHealthyIcon:
                if (menuWindow == null)
                    menuWindow = new SelectPicPopupWindow(this, this);
                //显示窗口 位置
                menuWindow.showAtLocation(main, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.textTakePhoto:
                SelectPicUtils.TakePhoto(this);
                menuWindow.dismiss();
                break;
            case R.id.textOpenPic:
                SelectPicUtils.PickPicture(this);
                menuWindow.dismiss();
                break;
            case R.id.textCancel:
                menuWindow.dismiss();
                break;
            case R.id.btnAddOldMan:
                if (checkData())
                    saveOldMan();
                break;
        }
    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioSex1:
                    sex = "男";
                    break;
                case R.id.radioSex2:
                    sex = "女";
                    break;
            }
        }
    };

    private Bitmap photo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case SelectPicUtils.PHOTO_REQUEST_GALLERY:
                SelectPicUtils.startPhotoZoom(this, data.getData());
                break;
            case SelectPicUtils.PHOTO_REQUEST_TAKE_PHOTO:
                SelectPicUtils.startPhotoZoom(this, Uri.fromFile(SelectPicUtils.tempFile));
                break;
            case SelectPicUtils.PHOTO_REQUEST_CUT:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap photo = bundle.getParcelable("data");
                    this.photo = photo;
                    img.setImageBitmap(photo);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveOldMan() {
        String name = editOldManName.getText().toString();
        String height = editHeight.getText().toString();
        String weight = editWeight.getText().toString();
        String appellation = editAppellation.getText().toString();
        String birth = editBirth.getText().toString();

        String userId = SharedPrefUtils.getString(this, SharedPrefUtils.USER_ID);
        String takenId = SharedPrefUtils.getString(this, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("tokenId", takenId);
            params.put("name", name);
            params.put("appellation", appellation);
            params.put("sex", sex);
            params.put("height", height);
            params.put("weight", weight);
            params.put("birthDate", birth);
            params.put("photo", ImageUtils.imageToBase64(new BitmapDrawable(getResources(), photo)));

            RequestManager.post(2, Urls.submitFamilyUser, params, this);
        }

    }

    private boolean checkData() {

        if (TextUtils.isEmpty(editOldManName.getText().toString())) {
            ToastUtils.show(this, "请填写名字");
            return false;
        }
        if (TextUtils.isEmpty(editHeight.getText().toString())) {
            ToastUtils.show(this, "请填写身高");
            return false;
        }
        if (TextUtils.isEmpty(editWeight.getText().toString())) {
            ToastUtils.show(this, "请填写体重");
            return false;
        }
        if (TextUtils.isEmpty(editAppellation.getText().toString())) {
            ToastUtils.show(this, "请填写称呼");
            return false;
        }
        if (TextUtils.isEmpty(editBirth.getText().toString())) {
            ToastUtils.show(this, "请填写出生日期");
            return false;
        }
        if (TextUtils.isEmpty(sex)) {
            ToastUtils.show(this, "请选择性别");
            return false;
        }
        if (photo == null) {
            ToastUtils.show(this, "请选择头像");
            return false;
        }
        return true;
    }
}
