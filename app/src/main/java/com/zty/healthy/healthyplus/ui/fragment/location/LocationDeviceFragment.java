package com.zty.healthy.healthyplus.ui.fragment.location;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;

/**
 * 设备信息
 * Created by zty on 2016/9/1.
 */
public class LocationDeviceFragment extends BaseFragment implements View.OnClickListener{

    private int count = 4;

    private RelativeLayout[] items = new RelativeLayout[count];
    private ImageView[] imageViews = new ImageView[count];
    private TextView[] textViews = new TextView[count];
    private TextView[] textValues = new TextView[count];

    private int[] itemIds = {R.id.itemDevice1, R.id.itemDevice2, R.id.itemDevice3, R.id.itemDevice4};

    private int iconId = R.id.imgDevice;
    private int textId = R.id.textDevice;
    private int textValueId = R.id.textDeviceState;

    private String[] strTips = {"设备编号：", "检测状态：", "电池电量：", "产品温度："};

    @Override
    public int getContentVew() {
        return R.layout.view_location_device;
    }

    @Override
    public void initView(View view) {
        for (int i = 0; i < count; i++) {
            items[i] = (RelativeLayout) view.findViewById(itemIds[i]);
            items[i].setOnClickListener(this);
            imageViews[i] = (ImageView) items[i].findViewById(iconId);
            textViews[i] = (TextView) items[i].findViewById(textId);
            textViews[i].setText(strTips[i]);
            textValues[i] = (TextView) items[i].findViewById(textValueId);
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

    }
}
