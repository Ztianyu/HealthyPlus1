package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.SugarDataModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.recycler.ViewHolder;

import java.util.List;

/**
 * Created by zty on 2016/10/20.
 */

public class SugarAdapter extends FooterRefreshAdapter<SugarDataModel> {

    public SugarAdapter(Context context, List<SugarDataModel> data) {
        super(context, data);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder viewHolder, SugarDataModel sugarDataModel) {
        ViewHolder holder = (ViewHolder) viewHolder;

        holder.setText(R.id.textWeightData1, TextUtils.isEmpty(sugarDataModel.getFgi()) ? sugarDataModel.getGi() : sugarDataModel.getFgi());
        holder.setText(R.id.textWeightData2, sugarDataModel.getGatherDate() + " " + sugarDataModel.getGatherTime());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_weight_data;
    }
}
