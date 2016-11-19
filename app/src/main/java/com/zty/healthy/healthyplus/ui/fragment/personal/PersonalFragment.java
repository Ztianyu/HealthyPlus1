package com.zty.healthy.healthyplus.ui.fragment.personal;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.activity.user.AboutUsActivity;
import com.zty.healthy.healthyplus.ui.activity.user.BindingActivity;
import com.zty.healthy.healthyplus.ui.activity.user.FeedbackActivity;
import com.zty.healthy.healthyplus.ui.activity.user.VersionActivity;

/**
 * Created by zty on 2016/8/16.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private int count = 7;

    private RelativeLayout[] items = new RelativeLayout[count];

    private int[] ids = {R.id.itemPerson1, R.id.itemPerson2, R.id.itemPerson3,
            R.id.itemPerson4, R.id.itemPerson5, R.id.itemPerson6, R.id.itemPerson7};

    @Override
    public int getContentVew() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView(View view) {
        ((BaseActivity) context).title.setText("个人中心");
        ((BaseActivity) context).left.setVisibility(View.INVISIBLE);
        ((BaseActivity) context).right.setVisibility(View.INVISIBLE);

        for (int i = 0; i < count; i++) {
            items[i] = (RelativeLayout) view.findViewById(ids[i]);
            items[i].setOnClickListener(this);
        }
    }

    @Override
    public void initData() {

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
            case R.id.itemPerson1:
                break;
            case R.id.itemPerson2:
                break;
            case R.id.itemPerson3:
                break;
            case R.id.itemPerson4:
                startActivity(new Intent(context, BindingActivity.class));
                break;
            case R.id.itemPerson5:
                startActivity(new Intent(context, FeedbackActivity.class));
                break;
            case R.id.itemPerson6:
                startActivity(new Intent(context, AboutUsActivity.class));
                break;
            case R.id.itemPerson7:
                startActivity(new Intent(context, VersionActivity.class));
                break;
        }

    }
}
