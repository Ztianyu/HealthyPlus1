package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.PressureDataModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.recycler.ViewHolder;

import java.util.List;

/**
 * Created by zty on 2016/10/20.
 */

public class PressureDataAdapter extends FooterRefreshAdapter<PressureDataModel> {

    public PressureDataAdapter(Context context, List<PressureDataModel> data) {
        super(context, data);

    }

    @Override
    protected void convert(RecyclerView.ViewHolder viewHolder, PressureDataModel pressureDataModel) {

        ViewHolder holder = (ViewHolder) viewHolder;

        holder.setText(R.id.textPressureData1, pressureDataModel.getHbp());
        holder.setText(R.id.textPressureData2, pressureDataModel.getLbp());
        holder.setText(R.id.textPressureData3, pressureDataModel.getHeart());
        holder.setText(R.id.textPressureData4, pressureDataModel.getGatherDateStr() + " " + pressureDataModel.getGatherTime());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_pressure_data;
    }
}
