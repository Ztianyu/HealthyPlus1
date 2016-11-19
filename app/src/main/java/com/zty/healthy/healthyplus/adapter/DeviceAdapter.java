package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.OldManDevices;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.recycler.ViewHolder;

import java.util.List;

/**
 * Created by zty on 2016/10/17.
 */

public class DeviceAdapter extends FooterRefreshAdapter<OldManDevices> {

    private BindingListener listener;

    public DeviceAdapter(Context context, List<OldManDevices> data, BindingListener listener) {
        super(context, data);
        this.listener = listener;
    }

    @Override
    protected void convert(RecyclerView.ViewHolder viewHolder, final OldManDevices deviceModel) {
        final ViewHolder holder = (ViewHolder) viewHolder;

        holder.setEdit(R.id.textDeviceOwn, deviceModel.getOwn());
        holder.setEdit(R.id.editSugarNum, TextUtils.isEmpty(deviceModel.getSugar()) ? "" : deviceModel.getSugar());
        holder.setEdit(R.id.editPressureNum, TextUtils.isEmpty(deviceModel.getPressure()) ? "" : deviceModel.getPressure());
        holder.setEdit(R.id.editWarningNum, TextUtils.isEmpty(deviceModel.getWarning()) ? "" : deviceModel.getWarning());
        holder.setEdit(R.id.editLocationNum, TextUtils.isEmpty(deviceModel.getLocation()) ? "" : deviceModel.getLocation());

        holder.setOnClick(R.id.btnSaveDevice, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sugarDevId = ((EditText) holder.getView(R.id.editSugarNum)).getText().toString();
                String pressureDevId = ((EditText) holder.getView(R.id.editPressureNum)).getText().toString();
                String fallDevId = ((EditText) holder.getView(R.id.editWarningNum)).getText().toString();
                String localizerDevId = ((EditText) holder.getView(R.id.editLocationNum)).getText().toString();

                listener.onBindingListener(2, deviceModel.getFamilyUserId(), sugarDevId, pressureDevId, fallDevId, localizerDevId);
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_device_content;
    }

    public interface BindingListener {
        void onBindingListener(int code, String familyUserId, String sugarDevId, String pressureDevId, String fallDevId, String localizerDevId);
    }
}
