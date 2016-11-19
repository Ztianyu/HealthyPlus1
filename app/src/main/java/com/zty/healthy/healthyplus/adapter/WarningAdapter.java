package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.MyBaseAdapter;
import com.zty.healthy.healthyplus.model.WarningModel;
import com.zty.healthy.healthyplus.widget.WholeListView;

import java.util.List;

/**
 * Created by zty on 2016/8/25.
 */
public class WarningAdapter extends MyBaseAdapter<WarningModel, ListView> {

    public WarningAdapter(Context context, List<WarningModel> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_warning, null);

            holder.textWarningName = (TextView) convertView.findViewById(R.id.textWarningName);
            holder.textWarningDate = (TextView) convertView.findViewById(R.id.textWarningDate);
            holder.textWarningContent = (TextView) convertView.findViewById(R.id.textWarningContent);
            holder.itemWarningTip1 = (TextView) convertView.findViewById(R.id.itemWarningTip1);
            holder.itemWarningTip2 = (TextView) convertView.findViewById(R.id.itemWarningTip2);
            holder.itemWarningTip3 = (TextView) convertView.findViewById(R.id.itemWarningTip3);
            holder.content = (WholeListView) convertView.findViewById(R.id.listWarning);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textWarningName.setText(mData.get(position).getWarningSource());
        holder.textWarningDate.setText(mData.get(position).getCreateDate());
        holder.textWarningContent.setText(mData.get(position).getRemarks());


        return convertView;
    }

    static class ViewHolder {

        TextView textWarningName;
        TextView textWarningDate;
        TextView textWarningContent;
        TextView itemWarningTip1;
        TextView itemWarningTip2;
        TextView itemWarningTip3;
        WholeListView content;
    }
}
