package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.AdviceModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.recycler.ViewHolder;
import com.zty.healthy.healthyplus.ui.activity.home.SchemeDetailsActivity;

import java.util.List;

/**
 * 健康建议
 * Created by zty on 2016/9/14.
 */
public class AdviceAdapter extends FooterRefreshAdapter<AdviceModel> {

    private String currentOldMan;

    public AdviceAdapter(Context context, List<AdviceModel> data) {
        super(context, data);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder viewHolder, final AdviceModel adviceModel) {
        ViewHolder holder = (ViewHolder) viewHolder;

//        holder.setText(R.id.textAdviceDate, "");
//        holder.setText(R.id.textAdviceName, "");
//        holder.setText(R.id.textAdviceIsNew, "");
//        holder.setText(R.id.textAdvicePosition, "");
//        holder.setText(R.id.textAdviceTitle, "");
//        holder.setText(R.id.textAdviceCount, "");

        holder.setOnClick(R.id.btnAdviceMore, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(mContext, SchemeDetailsActivity.class);
                intent.putExtra("scheme", adviceModel);
                mContext.startActivity(new Intent(mContext, SchemeDetailsActivity.class));

            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_advice_content;
    }
}
