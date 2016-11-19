package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.MyBaseAdapter;
import com.zty.healthy.healthyplus.model.AssessmentModel;

import java.util.List;

/**
 * Created by zty on 2016/10/9.
 */

public class ReportAdapter extends MyBaseAdapter<AssessmentModel.Assessment,ListView> {

    public ReportAdapter(Context context, List<AssessmentModel.Assessment> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_assessment, null);
            holder.textAssessmentName = (TextView) convertView.findViewById(R.id.textAssessmentName);
            holder.textAssessmentPro = (TextView) convertView.findViewById(R.id.textAssessmentPro);
            holder.textAssessmentResult = (TextView) convertView.findViewById(R.id.textAssessmentResult);
            holder.textAssessmentTime = (TextView) convertView.findViewById(R.id.textAssessmentTime);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textAssessmentName.setText(mData.get(position).getTypeNm());
        holder.textAssessmentPro.setText(mData.get(position).getQuestNm());
        holder.textAssessmentResult.setText(mData.get(position).getGradeNm());
        holder.textAssessmentTime.setText(mData.get(position).getCreateDate());

        return convertView;
    }

    static class ViewHolder {
        TextView textAssessmentName;
        TextView textAssessmentPro;
        TextView textAssessmentResult;
        TextView textAssessmentTime;
    }
}
