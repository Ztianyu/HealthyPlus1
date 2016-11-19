package com.zty.healthy.healthyplus.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;

/**
 * 健康数据表 头
 * Created by zty on 2016/10/20.
 */

public class HealthyDataTitleUtils {

    /**
     * 血压数据表头
     */
    public static void setPressureTitle(Context context, FrameLayout layout) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pressure_data, null);

        view.setBackgroundColor(context.getResources().getColor(R.color.colorBlack1));

        TextView textView1 = (TextView) view.findViewById(R.id.textPressureData1);
        TextView textView2 = (TextView) view.findViewById(R.id.textPressureData2);
        TextView textView3 = (TextView) view.findViewById(R.id.textPressureData3);
        TextView textView4 = (TextView) view.findViewById(R.id.textPressureData4);

        textView1.setTextColor(context.getResources().getColor(R.color.white));
        textView2.setTextColor(context.getResources().getColor(R.color.white));
        textView3.setTextColor(context.getResources().getColor(R.color.white));
        textView4.setTextColor(context.getResources().getColor(R.color.white));

        layout.addView(view);
    }

    /**
     * 血压数据表头
     */
    public static void setWeightTitle(Context context, FrameLayout layout, boolean isWeight) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_weight_data, null);

        view.setBackgroundColor(context.getResources().getColor(R.color.colorBlack1));

        TextView textView1 = (TextView) view.findViewById(R.id.textWeightData1);
        TextView textView2 = (TextView) view.findViewById(R.id.textWeightData2);

        textView1.setTextColor(context.getResources().getColor(R.color.white));
        textView2.setTextColor(context.getResources().getColor(R.color.white));

        if (isWeight) {
            textView1.setText("体重（kg）");
        } else {
            textView1.setText("血糖（mmol/L）");
        }


        layout.addView(view);
    }
}
