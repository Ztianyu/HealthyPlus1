package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.MyBaseAdapter;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

import java.util.List;

/**
 * 选择账户适配器
 * Created by zty on 2016/8/24.
 */
public class AccountAdapter extends MyBaseAdapter<OldManModel, ListView> {

    private String currentMember = "";

    public AccountAdapter(Context context, List<OldManModel> data) {
        super(context, data);
        currentMember = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_account, null);

            holder.icon = (CircularImageView) convertView
                    .findViewById(R.id.imgAccount);
            holder.name = (TextView) convertView
                    .findViewById(R.id.textAccountName);
            holder.isSelect = (ImageView) convertView
                    .findViewById(R.id.checkboxAccount);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        if (!currentMember.isEmpty()) {
//            if (mData.get(position).getAppellation().equals(currentMember)) {
//                holder.isSelect.setBackgroundResource(R.mipmap.ic_account_select);
//            } else {
//                holder.isSelect.setBackgroundResource(R.mipmap.ic_account_normal);
//            }
//        }

        holder.name.setText(mData.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        CircularImageView icon;
        TextView name;
        ImageView isSelect;
    }
}
