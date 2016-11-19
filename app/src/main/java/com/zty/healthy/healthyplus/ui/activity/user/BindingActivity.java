package com.zty.healthy.healthyplus.ui.activity.user;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.DeviceAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.manager.OldManAccountManger;
import com.zty.healthy.healthyplus.model.DeviceModel;
import com.zty.healthy.healthyplus.model.OldManDevices;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.OldManService;
import com.zty.healthy.healthyplus.utils.LayoutManager;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 绑定设备
 * Created by zty on 2016/9/1.
 */
public class BindingActivity extends BaseActivity implements View.OnClickListener, DeviceAdapter.BindingListener {

    private RecyclerView recyclerView;

    private List<OldManModel> oldMen;

    private OldManModel currentModel;

    private DeviceAdapter deviceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.view_recycler;
    }

    @Override
    protected void initView() {
        title.setText("设备绑定");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setVisibility(View.INVISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        deviceAdapter = new DeviceAdapter(this, new ArrayList<OldManDevices>(), this);

        LayoutManager.setVertical(this, recyclerView);
        recyclerView.setAdapter(deviceAdapter);

    }

    @Override
    protected void initData() {
        oldMen = OldManAccountManger.getInstance().getAccount();

        if (oldMen != null && oldMen.size() > 0) {

            for (int i = 0; i < oldMen.size(); i++) {
                currentModel = oldMen.get(i);
                OldManService.getOldManDevice(this, 1, oldMen.get(i).getId(), this);
            }
        }
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
                    List<DeviceModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<DeviceModel>>() {
                    }.getType());

                    if (models != null && models.size() > 0) {

                        OldManDevices oldManDevices = new OldManDevices();

                        oldManDevices.setOwn(currentModel.getAppellation());
                        oldManDevices.setFamilyUserId(currentModel.getId());

                        for (int i = 0; i < models.size(); i++) {
                            if (models.get(i).getDevType().equals("1")) {
                                oldManDevices.setPressure(models.get(i).getDevId());
                            } else if (models.get(i).getDevType().equals("2")) {
                                oldManDevices.setSugar(models.get(i).getDevId());
                            } else if (models.get(i).getDevType().equals("3")) {
                                oldManDevices.setWarning(models.get(i).getDevId());
                            } else if (models.get(i).getDevType().equals("4")) {
                                oldManDevices.setLocation(models.get(i).getDevId());
                            }
                        }
                        deviceAdapter.notifyBottomRefresh(oldManDevices);
                    }
                    break;
                case 2:
                    ToastUtils.show(this, "绑定成功！");
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
        }

    }

    @Override
    public void onBindingListener(int code, String familyUserId, String sugarDevId, String pressureDevId, String fallDevId, String localizerDevId) {
        OldManService.bindingDevice(this, code, familyUserId, sugarDevId, pressureDevId, fallDevId, localizerDevId, this);
    }
}
