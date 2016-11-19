package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.AssessClassModel;
import com.zty.healthy.healthyplus.model.AssessQuestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zty on 2016/10/22.
 */

public class AssessmentAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<AssessClassModel> mData;

    private Map<String, List<AssessQuestModel>> childrenData = new HashMap<>();

    public AssessmentAdapter(Context context, List<AssessClassModel> data) {
        this.context = context;
        this.mData = data;

    }

    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (mData == null) {
            return 0;
        } else if (childrenData.get(mData.get(groupPosition).getName()) != null) {
            return childrenData.get(mData.get(groupPosition).getName()).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData.get(mData.get(groupPosition).getName()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_assessment_title, null);
            holder.name = (TextView) convertView.findViewById(R.id.textAssessmentTitle);
            holder.imgAssessmentMore = (ImageView) convertView.findViewById(R.id.imgAssessmentMore);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(this.mData.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder2 holder = null;
        if (convertView == null) {
            holder = new ViewHolder2();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_assessment_title2, null);
            holder.name = (TextView) convertView.findViewById(R.id.textAssessmentTitle2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder2) convertView.getTag();
        }
        holder.name.setText(childrenData.get(mData.get(groupPosition).getName()).get(childPosition).getTitle());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {
        TextView name;
        ImageView imgAssessmentMore;
    }

    static class ViewHolder2 {
        TextView name;
    }

    public void setChildrenData(int position, List<AssessQuestModel> childrenData) {
        this.childrenData.put(mData.get(position).getName(), childrenData);
    }

}
