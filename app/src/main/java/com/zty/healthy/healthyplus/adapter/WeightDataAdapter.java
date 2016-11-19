package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.WeightModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.recycler.ViewHolder;

import java.util.List;

/**
 * Created by zty on 2016/10/20.
 */

public class WeightDataAdapter extends FooterRefreshAdapter<WeightModel> {

    public WeightDataAdapter(Context context, List<WeightModel> data) {
        super(context, data);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder viewHolder, WeightModel weightModel) {

        ViewHolder holder = (ViewHolder) viewHolder;

        holder.setText(R.id.textWeightData1, weightModel.getWeight());
        holder.setText(R.id.textWeightData2, weightModel.getGatherDate() + " " + weightModel.getGatherTime());

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_weight_data;
    }
}
