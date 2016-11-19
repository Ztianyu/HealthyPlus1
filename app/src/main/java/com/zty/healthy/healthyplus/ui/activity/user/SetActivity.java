package com.zty.healthy.healthyplus.ui.activity.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

/**
 * 设置界面
 * Created by zty on 2016/8/22.
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    private int count = 5;

    private RelativeLayout[] items = new RelativeLayout[count];
    private ImageView[] imageViews = new ImageView[count];
    private TextView[] textViews = new TextView[count];

    private int[] itemIds = {R.id.itemSetting1, R.id.itemSetting2, R.id.itemSetting3, R.id.itemSetting4, R.id.itemSetting5};

    private int iconId = R.id.imgSetting;
    private int textId = R.id.textSetting;

    private String[] strItems = {"设备绑定", "修改密码", "关于我们", "意见反馈", "版本信息"};

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {

        title.setText("设  置");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        for (int i = 0; i < count; i++) {
            items[i] = (RelativeLayout) findViewById(itemIds[i]);
            items[i].setOnClickListener(this);

            imageViews[i] = (ImageView) items[i].findViewById(iconId);
            textViews[i] = (TextView) items[i].findViewById(textId);
            textViews[i].setText(strItems[i]);
        }

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
            case R.id.itemSetting1:
                startActivity(new Intent(this, BindingActivity.class));
                break;
            case R.id.itemSetting2:
                startActivity(new Intent(this, ResetPassWordActivity.class));
                break;
            case R.id.itemSetting3:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.itemSetting4:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.itemSetting5:
                startActivity(new Intent(this, VersionActivity.class));
                break;
        }
    }
}
