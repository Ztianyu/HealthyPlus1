package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.MyBaseAdapter;
import com.zty.healthy.healthyplus.model.DoctorModel;
import com.zty.healthy.healthyplus.ui.activity.service.OrderDoctorActivity;

import java.util.List;

/**
 * Created by zty on 2016/10/11.
 */

public class PersonDoctorAdapter extends MyBaseAdapter {

    public PersonDoctorAdapter(Context context, List<DoctorModel> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_doctor, null);
            holder.img = (CircularImageView) convertView.findViewById(R.id.imgDoctor);
            holder.name = (TextView) convertView.findViewById(R.id.textDoctorName);
            holder.title = (TextView) convertView.findViewById(R.id.textDoctorTitle);
            holder.note = (TextView) convertView.findViewById(R.id.textDoctorNote);
            holder.btn = (Button) convertView.findViewById(R.id.btnDoctorOrder);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderDoctorActivity.class));
            }
        });

        return convertView;
    }

    static class ViewHolder {
        CircularImageView img;
        TextView name;
        TextView title;
        TextView note;
        Button btn;
    }
}
